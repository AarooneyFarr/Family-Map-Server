package Service;

import dao.AuthtokenDAO;
import dao.Database;
import dao.UserDAO;
import model.User;
import request.RegisterRequest;
import result.RegisterResponse;

/**
 * The Register service class.
 */
public class RegisterService{
    /**
     * Register user.
     *
     * @param request the request
     * @return the register response class
     */
    public RegisterResponse register(RegisterRequest request){
        Database db = new Database();

        try{
            db.openConnection();

            UserDAO uDAO = new UserDAO(db.getConnection());
            User existsChecker = uDAO.find(request.getUsername());

            if(existsChecker == null){
                User user = uDAO.createUser(request.getUsername(), request.getPassword(), request.getEmail(),
                                            request.getFirstName(), request.getLastName(), request.getGender());
                uDAO.fill(request.getUsername(), 4);

                String token = new AuthtokenDAO(db.getConnection()).createAuthtoken(user);

                db.closeConnection(true);

                RegisterResponse response = new RegisterResponse(token, user.getUsername(), user.getPersonID(), true,
                                                                 null);

                return response;
            }

            db.closeConnection(false);

            RegisterResponse response = new RegisterResponse(null, null, null, false, "Error: User already exists");
            return response;

        } catch(Exception ex){
            ex.printStackTrace();

            db.closeConnection(false);

            RegisterResponse response = new RegisterResponse(null, null, null, false, "Error: " + ex.getMessage());
            return response;
        }

    }
}
