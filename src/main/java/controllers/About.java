package controllers;
import dao.SessionsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/about")
@Controller
public class About {

    @Autowired
    SessionsDao sessionsDao;

    @GetMapping
    public String getAbout(@CookieValue(value= "sessionID", defaultValue = "0") String session){
        if(sessionsDao.checkExistingSession(session)){
            return "login";
        }
        return "about";
    }
}
