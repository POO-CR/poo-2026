public class TarifaClaro extends TarifaProveedor {

    @Override
    public double calcular(int totalSMS, int totalMinutos, int totalGigas) {
        double basico = super.calcular(totalSMS, totalMinutos, totalGigas);
        return basico * 1.2; // 20% extra sobre el total
    }

    @Override
    public String getNombre() {
        return "Claro";
    }
}
