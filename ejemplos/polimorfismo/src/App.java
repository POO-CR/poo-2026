
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese cantidad de SMS: ");
        int sms = sc.nextInt();
        System.out.print("Ingrese cantidad de minutos de llamada: ");
        int minutos = sc.nextInt();
        System.out.print("Ingrese cantidad de gigas de internet: ");
        int gigas = sc.nextInt();

        /* Polimorfismo estatico */
        System.out.println("\nTarifas por proveedor:");
        new TarifaClaro().calcular(sms, minutos, gigas);
        new TarifaPersonal().calcular(sms, minutos, gigas);
        new TarifaMovistar().calcular(sms, minutos, gigas);

        TarifaProveedor[] proveedores = {
                new TarifaClaro(),
                new TarifaPersonal(),
                new TarifaMovistar()
        };

        System.out.println("\nTarifas por proveedor:");
        for (TarifaProveedor proveedor : proveedores) {
            /* Polimorfismo dinamico */
            double total = proveedor.calcular(sms, minutos, gigas);
            System.out.printf("%s: $%.2f\n", proveedor.getNombre(), total);
        }
        sc.close();
    }
}
