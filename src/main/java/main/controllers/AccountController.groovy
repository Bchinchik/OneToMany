package main.controllers

import main.service.FileInfoService
import main.service.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@Controller

public class AccountController {
    @Autowired
    private RoleRepository accountRepository;
    @Autowired
    private FileInfoService fileInfoService
   // private List<resultAccount> resultAccountList;

    /* @RequestMapping("/get-roles")

     public List<Role> getAccounts() {
         List<Role> list = (List<Role>) accountRepository.findAll();
         return list;
     }*/

    @RequestMapping(value = "/dir", method = RequestMethod.GET)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
//, produces = MediaType.APPLICATION_XML_VALUE)
    public String dirFileList(@RequestParam(value = "path") String path,
                              @ModelAttribute("model") ModelMap model,
                              @AuthenticationPrincipal User principal) {//, HttpServletRequest request) {
        //def temp = PreAuthorize.f

//def temp = principal.getAuthorities().toString()
        if (principal.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            model.addAttribute("fileInfoList", fileInfoService.getFileList(path))
            //  model.addAttribute("userName",userName)
        } else {
            if (principal.getAuthorities().toString().equals("[ROLE_USER]")) {
                model.addAttribute("fileInfoList", fileInfoService.getFileList(path))
                //  model.addAttribute("userName",userName)
            }
        }
//        model.addAttribute("fileInfoList", fileInfoService.getFileListAdmin(path))
        return "FileInfo"
    }


    @RequestMapping(value = "/dir", method = RequestMethod.POST)
    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    //@PermitAll
    public String createFile(@RequestParam(value = "path") String path, @RequestBody String textFromRequestBody,
                             @ModelAttribute("model") ModelMap model, @AuthenticationPrincipal User principal) {
       // path += principal.getUsername().toString()+".txt"
        model.addAttribute("fileInfoList", fileInfoService.createFile(path, textFromRequestBody))
        return "FileInfo"

    }

    @RequestMapping(value = "/login")
    public String login() {
        //model.addAttribute("name", name);
        return "login"
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        //model.addAttribute("name", name);
        return "login"
    }
    /*@RequestMapping(value = "/dir", method = RequestMethod.PUT)
    public String updateFile(@RequestParam(value = "path") String path, @RequestBody String textFromRequestBody) {
        File file = new File(path)
        file.append("\n$textFromRequestBody")
        return "File updated successfully"
    }*/

    /*@RequestMapping("/get")
    //@ResponseBody
    public Map get() {
        // List<Role> list = (List<Role>) accountRepository.findAll();
        // List<Role> sList = new ArrayList<>();
        List<Role> mList = (List<Role>) accountRepository.findAll();
        def ammountMapGroupByCurrency = mList.groupBy { Role a -> a.roleName }.collectEntries { k, v -> [k, v.amount.sum()] }

        return ammountMapGroupByCurrency;
    }*/

    /*@RequestMapping("/get-account-status")
    //  @ResponseBody
    public List<resultAccount> getAccountStatus() {
        List<Role> currentAccountList = (List<Role>) accountRepository.findAll();



        List<resultAccount> result = new ArrayList();
        Map<String, Long> map = new HashMap<>();
        // List<Role> mList = currentAccountList;//Arrays.asList("aa1","cc2", "cc2", "aa2", "bb1");

        //result = currentAccountList.stream().sorted().;
        for (Role account : currentAccountList) {

            if (map.containsKey(account.getCurrName())) {

                map.put(account.getCurrName(), map.get(account.getCurrName()) + account.getAmount());

            } else {

                map.put(account.getCurrName(), account.getAmount());
            }
        }

        for (String key : map.keySet()) {

            result.add(new resultAccount(key, map.get(key)));
        }

        return result;*/

//        Collections.sort(currentAccountList, new Comparator<Role>() {
//            @Override
//            public int compare(Role o1, Role o2) {
//                return o1.getCurrName().compareTo(o2.getCurrName());
//            }
//        });
//        resultAccountList = new ArrayList<resultAccount>();
//
//        int currentAccountListCounter = 0;
//        while( currentAccountListCounter  < currentAccountList.size()-1){
//            resultAccountList.add(new resultAccount(currentAccountList.get(currentAccountListCounter).getCurrName(),currentAccountList.get(currentAccountListCounter).getAmount()));
//            int startGroupCounter = currentAccountListCounter;
//            int groupeIterCounter = 0;
//            while((startGroupCounter+1)<currentAccountList.size()&&(resultAccountList.get(resultAccountList.size()-1).getCurrName().equals(currentAccountList.get(startGroupCounter + 1).getCurrName())))  {
//                groupeIterCounter++;
//                resultAccountList.set(resultAccountList.size()-1, new resultAccount(currentAccountList.get(startGroupCounter).getCurrName(),resultAccountList.get(resultAccountList.size()-1).getSummAmount()+currentAccountList.get(startGroupCounter+1).getAmount()));
//                startGroupCounter++;
//
//            }
//            currentAccountListCounter++;
//            if(startGroupCounter != 0 ){currentAccountListCounter += groupeIterCounter;}
//        }
//        if (currentAccountListCounter <= currentAccountList.size() ) {
//            resultAccountList.add(new resultAccount(currentAccountList.get(currentAccountListCounter).getCurrName(), currentAccountList.get(currentAccountListCounter).getAmount()));//adding last item curent to next result
//        }
//        return resultAccountList;
    //   }


}
