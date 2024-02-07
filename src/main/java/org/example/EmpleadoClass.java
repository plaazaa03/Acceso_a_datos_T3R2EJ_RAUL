package org.example;

import java.math.BigDecimal;

public class EmpleadoClass {
    private int codemple;
    private String nombre;
    private String apellidos;
    private BigDecimal salario;
    private Integer dpto;
    private DepartamentoClass departamentoByDpto;

    public int getCodemple() {
        return codemple;
    }

    public void setCodemple(int codemple) {
        this.codemple = codemple;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Integer getDpto() {
        return dpto;
    }

    public void setDpto(Integer dpto) {
        this.dpto = dpto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpleadoClass that = (EmpleadoClass) o;

        if (codemple != that.codemple) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;
        if (salario != null ? !salario.equals(that.salario) : that.salario != null) return false;
        if (dpto != null ? !dpto.equals(that.dpto) : that.dpto != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codemple;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (salario != null ? salario.hashCode() : 0);
        result = 31 * result + (dpto != null ? dpto.hashCode() : 0);
        return result;
    }

    public DepartamentoClass getDepartamentoByDpto() {
        return departamentoByDpto;
    }

    public void setDepartamentoByDpto(DepartamentoClass departamentoByDpto) {
        this.departamentoByDpto = departamentoByDpto;
    }
}
