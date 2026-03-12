import java.util.Scanner;


public class Main {
    static String[][] sala = new String[10][10];


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < sala.length; i++) {
            for (int j = 0; j < sala[i].length; j++) {
                sala[i][j] = "[ ]";
            }
        }
        boolean salir = false;
        while (true) {
            System.out.println("Bienvenido, estas con las butacas de la sala actualemnte.");
            for (int i = 0; i < sala.length; i++) {
                for (int j = 0; j < sala[i].length; j++) {
                    System.out.print(sala[i][j]);
                }
                System.out.println();
            }
            System.out.println("Que desea hacer?");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Devolver entrada");
            System.out.println("3. Ver caja del dia (solo encargados)");
            System.out.println("4. Salir");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1: {
                    comprar_entradas();
                    break;
                }
                case 2: {
                    devolver_entradas();
                    break;
                }
                case 3: {
                    System.out.println("Introduce la contraseña");
                    String contraseña = sc.next();
                    if (contraseña.equals("1234")) {
                        double caixa = calcularCajaDia();
                        System.out.println("Hoy se han ganado " + caixa + "€");
                    } else {
                        System.out.println("Contraseña incorrecta");
                    }
                    break;
                }
                case 4: {
                    salir = true;
                    break;
                }
                default: {
                    System.out.println("Esa no es una opción");
                }
            }
            if (salir) {
                break;
            }
        }
    }


    //COMPRAR ENTRADAS
    public static void comprar_entradas() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige tu fila y asiento");
        System.out.println("Filas 0,1 i 2 son VIP (15€), las otras son normales(10€)");
        while (true) {
            int fila = sc.nextInt();
            int asiento = sc.nextInt();
            if (fila < 0 || fila > 9 || asiento < 0 || asiento > 9) {
                System.out.println("Posición no válida. Debe estar entre 0 y 9.");
                continue;
            }
            System.out.println("Has elegido la fila " + fila + " y el asiento " + asiento + ", son correctos?");
            String correcto = sc.next();
            if (sala[fila][asiento].equals("[ ]") && correcto.equals("si")) {
                int precio;
                if (fila <= 2) {
                    precio = 15;
                } else {
                    precio = 10;
                }
                sala[fila][asiento] = ("[X]");
                System.out.println("Introduce el dinero");
                int dinero = sc.nextInt();
                while (true) {
                    if (dinero > precio) {
                        int cambio = dinero - precio;
                        System.out.println("Gracias por su compra, el cambio son " + cambio + "€");
                        break;
                    } else if (dinero < precio) {
                        System.out.println("Dinero insuficiente, quedan" + (precio - dinero) + "€");
                        dinero = dinero + sc.nextInt();
                    } else {
                        System.out.println("Gracias por su compra, imprimiendo ticket");
                        break;
                    }
                }
                break;
            } else if (correcto.equals("no")) {
                System.out.println("Elige de nuevo las entradas");
            } else if (sala[fila][asiento].equals("[X]")) {
                System.out.println("Asiento ocupado, elige otro");
            } else {
                System.out.println("Respuesta incorrecta");
            }
        }
    }


    //metodo para el case 2 devolver entradas
    public static void devolver_entradas() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la fila (0-9)");
        int fila = sc.nextInt();
        System.out.println("Introduce la asiento (0-9)");
        int asiento = sc.nextInt();
        if (fila < 0 || fila > 9 || asiento < 0 || asiento > 9) {
            System.out.println("No has elegido tu posicion para tu asiento, recuerda que la primera fila es 0 y el primer asiento el numero 0.");
            return;
        }
        if (sala[fila][asiento].equals("[X]")) {
            sala[fila][asiento] = ("[ ]");
            System.out.println("La entrada ha sido devuelta correctamente");
            System.out.println("El dinero se te ha devuelto correctamente");
        } else {
            System.out.println("Este asiento esta libre");
        }
    }


    //Metodo para poder calcular la recaudacion de caja
    public static double calcularCajaDia() {
        double total = 0;
        for (int fila = 0; fila < sala.length; fila++) {
            for (int col = 0; col < sala[fila].length; col++) {
                if (sala[fila][col].equals("[X]")) {
                    if (fila <= 2) {
                        total = total + 15;
                    } else {
                        total = total + 10;
                    }
                }
            }
        }
        return total;
    }
}