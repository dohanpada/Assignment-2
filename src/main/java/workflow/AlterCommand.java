package workflow;

public class AlterCommand extends AbstractWorkflowCommand {

    private final int    ticketId;
    private final String field;
    private final String newValue;

    public AlterCommand(WorkflowBoard board,
                        int ticketId,
                        String field,
                        String newValue) {
        super(board);
        this.ticketId = ticketId;
        this.field    = field.toLowerCase();
        this.newValue = newValue;
    }

    @Override
    public void performCommand() {
        Ticket ticket = board.findTicketById(ticketId);
        if (ticket == null) {
            System.out.println("✘ Ticket #" + ticketId + " not found.");
            return;
        }

        String oldValue;
        switch (field) {
            case "title":
                oldValue = ticket.getTitle();
                ticket.setTitle(newValue);
                break;
            case "description":
                oldValue = ticket.getDescription();
                ticket.setDescription(newValue);
                break;
            case "priority":
                oldValue = ticket.getPriority();
                ticket.setPriority(newValue);
                break;
            default:
                System.out.println("✘ Unknown field '" + field
                        + "'. Valid fields: title, description, priority.");
                return;
        }

        String log = String.format("ALTER  | Ticket #%d field '%s': '%s' → '%s'",
                ticketId, field, oldValue, newValue);
        board.logCommand(log);
        System.out.println("✔ " + log);

        board.displayBoard();
    }
}