import com.sun.tools.javac.Main;

public class HotelApplication {
    /**
     *
     *The HotelApplication program implements an application that allow users to book for hotel rooms,
     *with a given check in and check out dates.
     *
     * @author Hanen Chemkhi
     *
     */

    public static void main(String[] args) {

        System.out.println("Welcome to the Hotel Reservation Application");
        while (true) {
            MainMenu.selectOption();
        }
    }
}
