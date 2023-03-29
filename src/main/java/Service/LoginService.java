package Service;

import dao.AuthtokenDAO;
import dao.Database;
import dao.UserDAO;
import model.User;
import request.LoginRequest;
import result.LoginResponse;
import result.RegisterResponse;

/**
 * The Login service class.
 */
public class LoginService{
    /**
     * Login the user service method.
     *
     * @param request the request
     * @return the login response class
     */
    public LoginResponse login(LoginRequest request){
        Database db = new Database();

        try{
            db.openConnection();

            UserDAO uDAO = new UserDAO(db.getConnection());

            boolean isValid = uDAO.validate(request.getUsername(), request.getPassword());

            if(isValid){
                User user = uDAO.find(request.getUsername());
                AuthtokenDAO aDAO = new AuthtokenDAO(db.getConnection());
                String token = aDAO.createAuthtoken(user);

                LoginResponse response = new LoginResponse(token, user.getUsername(), user.getPersonID(), true, null);

                db.closeConnection(true);

                return response;
            }

            db.closeConnection(false);

            LoginResponse response = new LoginResponse(null, null, null, false, "There was an error while logging in.");
            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            LoginResponse response = new LoginResponse(null, null, null, false, "Error: " + ex.getMessage());
            return response;
        }
    }
}
