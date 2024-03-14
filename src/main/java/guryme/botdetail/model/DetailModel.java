package guryme.botdetail.model;

import lombok.Data;

@Data
public class DetailModel {
    private String receiver;
    private String iban;
    private String edrpou;
    private String amount;
    private String destination;
}