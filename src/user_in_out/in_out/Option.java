package user_in_out.in_out;

public class Option {
    private int id;
    private String name;
    private Class<? extends Command> cmdClass;

    public Option(int id, String name, Class<? extends Command> cmdClass) {
        this.id = id;
        this.name = name;
        this.cmdClass = cmdClass;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public Class<? extends Command> getCmdClass() { return cmdClass; }

    public String toString() { return String.format("%-" + 3 + "s %s", id + ".", name); }
}