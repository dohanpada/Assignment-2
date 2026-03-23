package workflow;

public class CreateCommand extends AbstractWorkflowCommand {

    private final String title;
    private String description;
    private String priority;

    public CreateCommand(WorkflowBoard board, String title, String description, String priority) {
        super(board);
        this.title       = title;
        this.description = description;
        this.priority    = priority;
    }
    public CreateCommand(WorkflowBoard board, String title) {
        super(board);
        this.title       = title;
    }

    @Override
    public void performCommand() {
        Ticket ticket = new Ticket(title, description, priority);
        board.addTicketToColumn(ticket, "TO_DO");

        String log = String.format("CREATE | Ticket #%d '%s' created in TO_DO",
                ticket.getId(), ticket.getTitle());
        board.logCommand(log);
        System.out.println("correct " + log);

        board.displayBoard();
    }
}