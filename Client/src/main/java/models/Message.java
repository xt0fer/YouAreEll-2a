package models;

/* 
 * POJO for an Message object
 *
 *   {
    "sequence": "-",
    "timestamp": "_",
    "fromid": "xt0fer",
    "toid": "kristofer",
    "message": "Hello, Kristofer!"
  },

*
 */
public class Message { //implements Comparable {

    private String message = "";
    private String toid = "";
    private String fromid = "";
    private transient String timestamp = "-";
    private transient String sequence = "-";

    public Message (String message, String fromId, String toId) {
        this.message = message;
        this.fromid = fromId;
        this.toid = toId;
    }

    public Message (String message, String fromId) {
        this.message = message;
        this.fromid = fromId;
        this.toid = "";
    }

    @Override
    public String toString() {
        return "to: " + this.toid + ", from: "+ this.fromid + ", " + this.message + ";";
    }

    public int compareTo(Message o) {
        return this.sequence.compareTo(o.getSequence());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToid() {
        return toid;
    }

    public void setToid(String toid) {
        this.toid = toid;
    }

    public String getFromId() {
        return fromid;
    }

    public void setFromId(String fromId) {
        this.fromid = fromId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getSequence() {
        return sequence;
    }
}