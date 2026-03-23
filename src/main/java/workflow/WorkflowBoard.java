package workflow;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WorkflowBoard {

    private final Map<String, List<Ticket>> columns;
    private final List<String> auditLog;

    public WorkflowBoard() {
        columns  = new LinkedHashMap<>();
        auditLog = new ArrayList<>();

        columns.put("TO_DO",       new ArrayList<>());
        columns.put("IN_PROGRESS", new ArrayList<>());
        columns.put("DONE",        new ArrayList<>());
    }

    public void addTicketToColumn(Ticket ticket, String column) {
        getColumn(column).add(ticket);
    }

    public Ticket findTicketById(int id) {
        for (List<Ticket> tickets : columns.values()) {
            for (Ticket t : tickets) {
                if (t.getId() == id) return t;
            }
        }
        return null;
    }

    public String findColumnOfTicket(int id) {
        for (Map.Entry<String, List<Ticket>> entry : columns.entrySet()) {
            for (Ticket t : entry.getValue()) {
                if (t.getId() == id) return entry.getKey();
            }
        }
        return null;
    }

    public boolean removeTicketFromColumn(int id, String column) {
        return getColumn(column).removeIf(t -> t.getId() == id);
    }

    public List<Ticket> getColumn(String column) {
        return columns.getOrDefault(column, new ArrayList<>());
    }

    public boolean columnExists(String column) {
        return columns.containsKey(column);
    }

    public void logCommand(String entry) {
        auditLog.add(entry);
    }

    public List<String> getAuditLog() {
        return auditLog;
    }

    public void displayBoard() {
        System.out.println("Board State");

        for (Map.Entry<String, List<Ticket>> entry : columns.entrySet()) {
            System.out.println("\n  ── " + entry.getKey() + " ──");
            if (entry.getValue().isEmpty()) {
                System.out.println("    (empty)");
            } else {
                for (Ticket t : entry.getValue()) {
                    System.out.println(t);
                }
            }
        }
        System.out.println();
    }
}