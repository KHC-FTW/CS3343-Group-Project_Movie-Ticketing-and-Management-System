package release.in_out;

import release.command.Command;

public class Option {
    private String name;
    private Class<? extends Command> cmdClass;

    public Option(String name, Class<? extends Command> cmdClass) {
        this.name = name;
        this.cmdClass = cmdClass;
    }

    public String getName() { return name; }

    public Class<? extends Command> getCmdClass() { return cmdClass; }

    public String toString() { return name; }
}