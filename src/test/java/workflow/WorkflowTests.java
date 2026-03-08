package workflow;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkflowTests {

    @Test
    public void createAddsTicketToBoard() {

        WorkflowBoard board = new WorkflowBoard();

        new CreateCommand(board, "Login Feature").performCommand();

        assertEquals(1, board.getToDoTickets().size());
    }

    @Test
    public void commandsAreLogged() {

        WorkflowBoard board = new WorkflowBoard();

        new CreateCommand(board, "Task").performCommand();

        assertTrue(board.getAuditLog().size() >= 1);
    }

}