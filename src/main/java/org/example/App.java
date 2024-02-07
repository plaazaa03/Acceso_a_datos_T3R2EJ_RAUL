package org.example;


import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    private static Session session;

    public static void main( String[] args )
    {
        SessionFactory sessionFactory = (SessionFactory) new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        if (session != null) {
            System.out.println("Se inició la sesión");

            Scanner scanner = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("========MENU========");
                System.out.println("1. Listar todos los empleados");
                System.out.println("2. Listar todos los departamentos");
                System.out.println("3. Mostrar un empleado por el Codigo");
                System.out.println("4. Mostar todos los empleados que pertenezcan a un departamento");
                System.out.println("5. Mostrar Info de cada departamento");
                System.out.println("6. Salir del programa");
                System.out.println("Introduzca su opcion: ");

                opcion=scanner.nextInt();

                switch (opcion){
                    case 1:
                        listarEmpleados();
                        break;
                    case 2:
                        listarDepartamentos();
                        break;
                    case 3:
                        mostrarEmpleadoPorCodigo();
                        break;
                    case 4:
                        mostrarEmpleadosDepartamento();
                        break;
                    case 5:
                        mostarInfoDepartamento();
                        break;
                    case 6:
                        System.out.println("Saliendo del programa, hasta la proxima!!!!");
                }
            }while(opcion != 6);
            scanner.close();
        }
        else {
            System.out.println("Error abriendo la sesión");
        }
    }

    private static void listarDepartamentos() {
        DepartamentoClass DepartamentoClass = new DepartamentoClass();
        Query query = session.createQuery("from DepartamentoClass ");
        List<DepartamentoClass> lista = query.list();
        Iterator <DepartamentoClass> iterator = lista.iterator();

        while (iterator.hasNext()){
            DepartamentoClass = (DepartamentoClass) iterator.next();
            System.out.println("==========================");
            System.out.println("Codigo: " + DepartamentoClass.getCodept());
            System.out.println("Nombre: " + DepartamentoClass.getNombre());
            System.out.println("==========================");
        }

    }

    private static void mostrarEmpleadoPorCodigo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el codigo del empleado que desea buscar: ");
        int codEmpleado = scanner.nextInt();



        EmpleadoClass EmpleadoClass = session.get(EmpleadoClass.class, codEmpleado);

        if (EmpleadoClass != null){
            System.out.println("==========================");
            System.out.println("Codigo: " + EmpleadoClass.getCodemple());
            System.out.println("Nombre: " + EmpleadoClass.getNombre());
            System.out.println("Apellidos: " + EmpleadoClass.getApellidos());
            System.out.println("Salario: " + EmpleadoClass.getSalario());
            System.out.println("Departamento: " + EmpleadoClass.getDpto());
            System.out.println("==========================");
        }else {
            System.out.println("No se ha encontrado a ningun empleado con el codigo: " +codEmpleado);
        }

    }

    private static void mostrarEmpleadosDepartamento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el departamento del cliente: ");
        int codDepartamento = scanner.nextInt();

        Query query = session.createQuery("from EmpleadoClass where dpto =:codigo");
        query.setParameter("codigo", codDepartamento);


        DepartamentoClass departamentoClass = session.get(DepartamentoClass.class, codDepartamento);

        if (departamentoClass != null){
            System.out.println("Departamento: " + departamentoClass.getCodept() + "- " + departamentoClass.getNombre());
            System.out.println("==========================");

            List<EmpleadoClass> empleadoClasses = query.getResultList();

            for (EmpleadoClass EmpleadoClass : empleadoClasses){
                System.out.println("==========================");
                System.out.println("Codigo: " + EmpleadoClass.getCodemple());
                System.out.println("Nombre: " + EmpleadoClass.getNombre());
                System.out.println("Apellidos: " + EmpleadoClass.getApellidos());
                System.out.println("Salario: " + EmpleadoClass.getSalario());
                System.out.println("Departamento: " + EmpleadoClass.getDpto());
                System.out.println("==========================");
            }

        }else{
            System.out.println("No se ha encontrado ningun departamento con el codigo " + codDepartamento);
        }
    }

    private static void mostarInfoDepartamento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca el codigo del departamento: ");
        int codDepartamento = scanner.nextInt();

        String hql = "select d.codept, d.nombre, count(e.codemple), avg(e.salario) " +
                "from DepartamentoClass d " +
                "join EmpleadoClass e on d.codept = e.dpto " +
                "where d.codept = :codigoDepartamento " +
                "group by d.codept, d.nombre";

        Query<Objects[]> query = session.createQuery(hql);
        query.setParameter("codigoDepartamento", codDepartamento);
        List<Objects[]> resultado = query.getResultList();

        if (!resultado.isEmpty()){
            Object[] result = resultado.get(0);
            System.out.println("==========================");
            System.out.println("Codigo del departamento: " + result[0]);
            System.out.println("Nombre del departamento: " + result[1]);
            System.out.println("Número de empleados: " + result[2]);
            System.out.println("Media de salarios: " + result[3]);
            System.out.println("==========================");
        }else{
            System.out.println("No se encontro nada sobre el codigo de departamento "+ codDepartamento);
        }

    }

    private static void listarEmpleados() {
        EmpleadoClass EmpleadoClass = new EmpleadoClass();
        Query query = session.createQuery("from EmpleadoClass ");
        List<EmpleadoClass> lista = query.list();
        Iterator <EmpleadoClass> iterator = lista.iterator();

        while (iterator.hasNext()){
            EmpleadoClass = (EmpleadoClass) iterator.next();
            System.out.println("==========================");
            System.out.println("Codigo: " + EmpleadoClass.getCodemple());
            System.out.println("Nombre: " + EmpleadoClass.getNombre());
            System.out.println("Apellidos: " + EmpleadoClass.getApellidos());
            System.out.println("Salario: " + EmpleadoClass.getSalario());
            System.out.println("Departamento: " + EmpleadoClass.getDpto());
            System.out.println("==========================");
        }
    }
}
