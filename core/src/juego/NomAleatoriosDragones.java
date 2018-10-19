package juego;

public class NomAleatoriosDragones<nomAleatorio> {

    public static String[] generarNombreAleatorio(int cantidad) {
        String[] nomAleatorioDragones = new String[cantidad];

        String[] nombres = {"Zu", "Draco", "Quijote", "Griffin", "Zeus", "Malefica", "Chango", "Mattei",
                "Mickey", "Zoliban", "Goku", "Lilo", "Oscar", "Carlos", "Guffi", "Makuin", "Matte", "Mono",
                "Cronos", "Gea", "Robot", "Apolo", "Ministro", "Poseidon", "Eros", "Hades", "Hera", "Eros",
                "Pan", "Dulce", "Rita", "Soplo", "Come", "Zombi", "Rakitich", "Messi", "Cristiano", "Navas",
                "Sapri", "Leon", "Drake", "Joss", "Pugi", "Mister Pigy"};
        String[] apellidos = {"Zuzunaga", "Sorni", "Garza", "Sandemiro", "Urriaga", "Tribilin", "Tintin",
                "Tatan", "Arrugada", "De la Mancha", "Vita", "Pregonas", "Gona", "Gonorrea", "Sazon", "Sorda",
                "Enamorada", "Vibora", "Cama", "Piesplanos", "Acolor", "Apolo", "Tenedor", "Delfin", "Verdugo",
                "Michi", "Feo", "Lagartija", "Jirafon", "Costillos", "Anacleto", "Nuero", "Barato", "Viejobueno",
                "Cayado", "Sincejas", "Sinboca", "Sinrazgon", "Sinpelos", "Nerry", "Dragon", "Papon", "Joggi"};

        for (int i = 0; i < cantidad; i++) {
            nomAleatorioDragones[i] = nombres[(int) (Math.floor(Math.random() * ((nombres.length - 1) - 0 + 1) + 0))] + " " + apellidos[(int) (Math.floor(Math.random() * ((apellidos.length - 1) - 0 + 1) + 0))];
        }
        return nomAleatorioDragones;
    }


    public static void nomAleatorio() {
        String [] nom = generarNombreAleatorio(1);
        System.out.println(nom[0]);
    }
}
