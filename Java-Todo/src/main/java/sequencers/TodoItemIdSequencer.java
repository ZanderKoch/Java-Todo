package sequencers;

public class TodoItemIdSequencer extends IdSequencer {
    private static final TodoItemIdSequencer INSTANCE = new TodoItemIdSequencer();

    private TodoItemIdSequencer() {}

    public static TodoItemIdSequencer getInstance() {
        return INSTANCE;
    }
}