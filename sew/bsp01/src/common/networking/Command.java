package common.networking;

import java.io.Serializable;

public class Command implements Serializable {

    private CommandType type;
    private Object value;

    public Command(CommandType type) {
        this.type = type;
    }

    public Command(CommandType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
