package BUS;

import DAO.DoiMK_DAO;
import DTO.DoiMK_DTO;

public class DoiMK_BUS {
    private DoiMK_DAO doiMK_DAO;

    public DoiMK_BUS() {
        doiMK_DAO = new DoiMK_DAO();
    }

    public boolean changePassword(DoiMK_DTO doiMK_DTO) {
        String username = doiMK_DTO.getUsername();
        String oldPassword = doiMK_DTO.getOldPassword();
        String newPassword = doiMK_DTO.getNewPassword();
        
        // Gọi phương thức changePassword từ DoiMK_DAO để kiểm tra và cập nhật mật khẩu
        return doiMK_DAO.changePassword(username, oldPassword, newPassword);
    }
    
}
