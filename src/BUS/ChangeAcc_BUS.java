package BUS;

import java.util.ArrayList;

import DAO.ChangeAcc_DAO;
import DTO.Account_DTO;

public class ChangeAcc_BUS {
    private ArrayList<Account_DTO> dsAcc;

    public ChangeAcc_BUS() {
    }

    public void ListAcc() {
        ChangeAcc_DAO accDAO = new ChangeAcc_DAO();

        dsAcc = new ArrayList<>();
        dsAcc = accDAO.list();
    }

    public void updateACC(Account_DTO s) {
        ListAcc();
        for (int i = 0; i < dsAcc.size(); i++) {
            if (dsAcc.get(i).getUsername().equals(s.getUsername())) {
                dsAcc.set(i, s);
                ChangeAcc_DAO AccDAO = new ChangeAcc_DAO();
                AccDAO.Update(s);
                System.out.println("TÌm thấy");
                return;
            }
        }
        System.out.println("Không tìm thấy Username");
    }

    public boolean KtraUsername(String username) {
        ListAcc();
        for (int i = 0; i < dsAcc.size(); i++) {
            if (dsAcc.get(i).getUsername().equals(username)) {
                System.out.println("TÌm thấy TK");
                return true;
            }
        }
        return false;
    }

    public void Add(Account_DTO acc) {
        ChangeAcc_DAO addAcc = new ChangeAcc_DAO();
        addAcc.Add(acc);
    }
}
