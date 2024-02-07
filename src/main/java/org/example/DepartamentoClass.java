package org.example;

import java.util.Collection;

public class DepartamentoClass {
    private int codept;
    private String nombre;
    private Collection<EmpleadoClass> empleadosByCodept;

    public int getCodept() {
        return codept;
    }

    public void setCodept(int codept) {
        this.codept = codept;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartamentoClass that = (DepartamentoClass) o;

        if (codept != that.codept) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codept;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    public Collection<EmpleadoClass> getEmpleadosByCodept() {
        return empleadosByCodept;
    }

    public void setEmpleadosByCodept(Collection<EmpleadoClass> empleadosByCodept) {
        this.empleadosByCodept = empleadosByCodept;
    }
}
