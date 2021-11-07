package com.dammak.project401.controller;

import com.dammak.project401.HospitalRepo;
import com.dammak.project401.Security.HospitalDetailsServicelmpl;
import com.dammak.project401.Security.UserDetailsServiceImpl;
import com.dammak.project401.UserRepo;
import com.dammak.project401.models.AppUser;
import com.dammak.project401.models.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserController {
    @Autowired
    HospitalRepo hospitalRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    HospitalDetailsServicelmpl hospitalDetailsServicelmpl;
    @Autowired
    PasswordEncoder encoder;


    @GetMapping("/login")
    public String getLoginPage(){return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }


    @PostMapping("/signup")
    public RedirectView signUpUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String blodType
            , @RequestParam String placeName , @RequestParam String emailAdress , @RequestParam String phoneNum  ){
        AppUser appUser = new AppUser(username, encoder.encode(password),firstName,lastName,dateOfBirth,blodType,placeName,emailAdress,phoneNum,"ROLE_USER");
        userRepo.save(appUser);

        return new RedirectView("/login");

    }

    @GetMapping("/user/{id}")
    public String getUser(Principal p, Model model, @PathVariable Long id) {
        model.addAttribute("usernamePrincipal", p.getName());
        AppUser appUser = userRepo.findById(id).get();
        model.addAttribute("userInformation", appUser);
        return "users.html";
    }
    @GetMapping("/newarehospital")
    public String getNearHospital(Principal p,Model m){
        AppUser appUser= userRepo.findByUserName(p.getName());
        List<Hospital> nearHospital = hospitalRepo.findAllByPlaceName(appUser.getPlaceName());
        if (appUser.getHospitals().isEmpty()){
m.addAttribute("isHaveHospital",false);
m.addAttribute("neareHospital",nearHospital);
            return "nearhospital";

        }
        Set<Hospital> removeHospital = appUser.getHospitals();
for (Hospital hospital : removeHospital){
    if (nearHospital.contains(hospital)){
        nearHospital.remove(hospital);
    }
}
        m.addAttribute("isHaveHospital",true);
        m.addAttribute("hospitalHave",appUser.getHospitals());
        m.addAttribute("neareHospital",nearHospital);

        return "nearhospital";

    }
    @PutMapping("/addhospital/{hospitalId}")
    public String addHospital(@PathVariable Long hospitalId, Principal p,Model m){
        Hospital hospital = hospitalRepo.findById(hospitalId).get();
        AppUser appUser = userRepo.findByUserName(p.getName());
if (hospital.getDonors().contains(appUser)){
    hospital.getDonors().remove(appUser);

}else {
    hospital.getDonors().add(appUser);
}
        hospitalRepo.save(hospital);
return "/nearehospital";
    }
@GetMapping("/getDonors/{type}")
    public String getDoners(Principal p,Model m,@PathVariable String type){
    ArrayList<AppUser> doonersList = new ArrayList<>();
       Hospital hospital = hospitalRepo.findByUserName(p.getName());
       if(type == "all"){
           m.addAttribute("doners",hospital.getDonors());
           return "doners";
       }else if (type == "o+"){
           for (AppUser doners : hospital.getDonors()){
               if(doners.getBlodType()=="o+"){
                   doonersList.add(doners);
               }
           }

       }else if (type == "o-"){
           for (AppUser doners : hospital.getDonors()) {
               if (doners.getBlodType() == "o-") {
                   doonersList.add(doners);
               }

           } }else if (type == "a+"){
               for (AppUser doners : hospital.getDonors()) {
                   if (doners.getBlodType() == "a+") {
                       doonersList.add(doners);
                   }

               }}else if (type == "a-"){
                   for (AppUser doners : hospital.getDonors()) {
                       if (doners.getBlodType() == "a-") {
                           doonersList.add(doners);
                       }

                   }}else if (type == "b+"){
                       for (AppUser doners : hospital.getDonors()) {
                           if (doners.getBlodType() == "b+") {
                               doonersList.add(doners);
                           }

                       }}else if (type == "b-"){
                           for (AppUser doners : hospital.getDonors()) {
                               if (doners.getBlodType() == "b-") {
                                   doonersList.add(doners);
                               }

                           }}else if (type == "ab+"){
                               for (AppUser doners : hospital.getDonors()) {
                                   if (doners.getBlodType() == "ab+") {
                                       doonersList.add(doners);
                                   }

                               } }else{
                                   for (AppUser doners : hospital.getDonors()) {
                                       if (doners.getBlodType() == "ab-") {
                                           doonersList.add(doners);
                                       }

                                   }
                               }
m.addAttribute("doners",doonersList);

       return  "doners";
}




}
