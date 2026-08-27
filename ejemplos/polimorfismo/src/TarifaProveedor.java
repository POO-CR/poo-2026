public abstract class TarifaProveedor {

    private static final double PRECIO_SMS = 1.0;
    private static final double PRECIO_MINUTO = 15.0;
    private static final double PRECIO_GIGA = 20.0;

    public double calcular(int totalSMS, int totalMinutos, int totalGigas) {
        return calcularSMS(totalSMS) + calcularMinutosDeLlamada(totalMinutos) + calcularConsumoGB(totalGigas);
    }

    protected double calcularSMS(int totalSMS) {
        return totalSMS * PRECIO_SMS;
    }

    protected double calcularMinutosDeLlamada(int totalMinutos) {
        return totalMinutos * PRECIO_MINUTO;
    }

    protected double calcularConsumoGB(int totalGigas) {
        return totalGigas * PRECIO_GIGA;
    }

    public abstract String getNombre();
}
