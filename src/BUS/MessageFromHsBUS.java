package BUS;

import java.util.*;

import DAO.MessageFromHsDAO;
import DTO.MessageFromHsDTO;

public class MessageFromHsBUS {
    private ArrayList<MessageFromHsDTO> dsmf;

    public void list() {
        MessageFromHsDAO mDao = new MessageFromHsDAO();
        dsmf = new ArrayList<>();
        dsmf = mDao.list();
    }

    public ArrayList<MessageFromHsDTO> getList() {
        return dsmf;
    }

    public void getListSearch(ArrayList<String> mss) {
        if (mss == null) {
            throw new IllegalArgumentException("Search parameters cannot be null");
        }

        MessageFromHsDAO dao = new MessageFromHsDAO();

        try {
            dsmf = new ArrayList<>();
            dsmf = dao.getListSearch(mss);
            if (dsmf == null) {
                System.out.println("null");
            }
        } catch (Exception e) {
            // Handle the exception appropriately, e.g., logging it and initializing an
            // empty list
            System.err.println("An error occurred while fetching search results: " + e.getMessage());
            dsmf = new ArrayList<>();
        }
    }

    public void update(ArrayList<String> uptt) {
        MessageFromHsDAO dao = new MessageFromHsDAO();
        dao.Update(uptt);
    }

    public String getIMG(String mahs) {
        String img = null;
        MessageFromHsDAO dao = new MessageFromHsDAO();
        img = dao.getIMG(mahs);
        return img;
    }
}
