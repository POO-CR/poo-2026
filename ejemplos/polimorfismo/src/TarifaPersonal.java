public class TarifaPersonal extends TarifaProveedor {
    
    @Override
    protected double calcularMinutosDeLlamada(int totalMinutos) {
        return super.calcularMinutosDeLlamada(totalMinutos) * 1.2; // 20% extra sobre minutos
    }

    @Override
    protected double calcularConsumoGB(int totalGigas) {
        return super.calcularConsumoGB(totalGigas) * 1.5; // 50% extra sobre GB
    }

    @Override
    public String getNombre() {
        return "Personal";
    }
}
