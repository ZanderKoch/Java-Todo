package sequencers;

public class PersonIdSequencer extends IdSequencer {
    private static final PersonIdSequencer INSTANCE = new PersonIdSequencer();

    private PersonIdSequencer() {}

    public static PersonIdSequencer getInstance() {
        return INSTANCE;
    }
}