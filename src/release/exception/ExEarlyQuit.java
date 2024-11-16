package release.exception;

public class ExEarlyQuit extends Exception{
    // exMsg: can freely input quitting from which part e.g. booking movie seats, buying snacks/drinks, making payment, etc.
    public ExEarlyQuit(String exMsg){super("[State] You have early quit from " + exMsg + " without completing the process.");}
}
