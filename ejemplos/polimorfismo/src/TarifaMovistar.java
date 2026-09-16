public class TarifaMovistar extends TarifaProveedor {
    @Override
    protected double calcularSMS(int totalSMS) {
        return super.calcularSMS(totalSMS) * 1.1; // 10% extra sobre SMS
    }

    @Override
    protected double calcularMinutosDeLlamada(int totalMinutos) {
        return super.calcularMinutosDeLlamada(totalMinutos) * 1.2; // 20% extra sobre minutos
    }

    @Override
    protected double calcularConsumoGB(int totalGigas) {
        return super.calcularConsumoGB(totalGigas) * 1.3; // 30% extra sobre GB
    }

    @Override
    public String getNombre() {
        return "Movistar";
    }
}
