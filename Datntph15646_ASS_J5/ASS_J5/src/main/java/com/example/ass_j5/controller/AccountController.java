package com.example.ass_j5.controller;

import com.example.ass_j5.entity.Account;
import com.example.ass_j5.repositories.AccountRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AccountController {
    @Autowired
    private AccountRepositories accountRepositories;

    @Autowired
    private HttpServletRequest request;


    @PostMapping("/doipass/{Id}")
    public String doipass(HttpServletRequest request, HttpServletResponse response, @PathVariable String Id){
        String pass = request.getParameter("pass");
        String passmoi = request.getParameter("passmoi");
        String laipass = request.getParameter("laipass");
  if (passmoi.equals(laipass)){
      Account account = accountRepositories.findByIdAndPass(Id,pass);
     account.setPass(passmoi);
     accountRepositories.save(account);
     return "redirect:/logout";
  }



    return "redirect:/home";
    }
    @PostMapping("/signup")
    public String store(ModelMap modelMap,
                        @Valid @ModelAttribute("acc") Account acc,
                        BindingResult bindingResult //validation ktra
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        try {
            acc.setId(UUID.randomUUID().toString().replace("-", ""));
            acc.setAdmin(0);
            acc.setUpdelete(1);
            acc.setSell(1);
            accountRepositories.save(acc);


            return "redirect:/home";
        } catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            return "login";
        }
        //  return "redirect:create";
    }

    @GetMapping("/loginacc")
    public String getAlltk(ModelMap modelMap) {

        modelMap.addAttribute("acc", new Account());
        return "login";
    }

    @GetMapping("/webcam")
    public String getwebcam(ModelMap modelMap) {
        return "Webcam";
    }

    @PostMapping("/loginacc")
    public String getAlltk(ModelMap modelMap, @Valid @ModelAttribute("acc") Account acc,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {//bindingResult.hasErrors()==true không đúng với yêu cầu model
            return "login";
        }
        try {
            Account account = accountRepositories.findByEmailAndPass(acc.getEmail(), acc.getPass());
            HttpSession session = request.getSession();
            session.setAttribute("acc", account);
            session.setMaxInactiveInterval(60 * 60 * 24);
            return "redirect:home";

        } catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            return "login";
        }

    }

    @GetMapping("/logout")
    public String logout() {
        HttpSession session = request.getSession();
        session.removeAttribute("acc");
        return "redirect:home";
    }
    @GetMapping("/QR")
    public String Qrcode() {
        return "Webcam";
    }
    @GetMapping("/QRquet/id=?")
    public String quetQrcode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        Account a = accountRepositories.findById(id).get();
        if (a==null) {
            request.setAttribute("mess", "vui lòng xem lại mã");
            request.getRequestDispatcher("login").forward(request, response);
        }
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
             return "redirect:home";
    }
}
