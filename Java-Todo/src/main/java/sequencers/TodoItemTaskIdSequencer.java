package sequencers;

public class TodoItemTaskIdSequencer extends IdSequencer {
    private static final TodoItemTaskIdSequencer INSTANCE = new TodoItemTaskIdSequencer();

    private TodoItemTaskIdSequencer() {
    }

    public static TodoItemTaskIdSequencer getInstance() {
        return INSTANCE;
    }
}