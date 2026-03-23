package workflow;

public class MoveCommand extends AbstractWorkflowCommand {

    private final int    ticketId;
    private final String toColumn;

    public MoveCommand(WorkflowBoard board, int ticketId, String toColumn) {
        super(board);
        this.ticketId = ticketId;
        this.toColumn = toColumn.toUpperCase().replace(" ", "_");
    }

    @Override
    public void performCommand() {
        Ticket ticket = board.findTicketById(ticketId);
        if (ticket == null) {
            System.out.println("Wrong Ticket number" + ticketId + " not found.");
            return;
        }
        if (!board.columnExists(toColumn)) {
            System.out.println("Wrong Column '" + toColumn + "' does not exist.");
            return;
        }

        String fromColumn = board.findColumnOfTicket(ticketId);
        if (fromColumn.equals(toColumn)) {
            System.out.println("Wrong Ticket number" + ticketId + " is already in " + toColumn);
            return;
        }

        board.removeTicketFromColumn(ticketId, fromColumn);
        board.addTicketToColumn(ticket, toColumn);
        ticket.setStatus(Status.valueOf(toColumn));

        String log = String.format("MOVE   | Ticket #%d '%s' moved: %s → %s",
                ticketId, ticket.getTitle(), fromColumn, toColumn);
        board.logCommand(log);
        System.out.println("correct " + log);

        board.displayBoard();
    }
}