package com.ecust.sharebook.controller.app;

import com.alibaba.fastjson.parser.SymbolTable;
import com.ecust.sharebook.mapper.BookCircleInfMapper;
import com.ecust.sharebook.pojo.*;
import com.ecust.sharebook.service.TBcircleMemberService;
import com.ecust.sharebook.service.TBookCircleService;
import com.ecust.sharebook.service.TMemberService;
import com.ecust.sharebook.utils.Jwt.JwtUtil;
import com.ecust.sharebook.utils.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * written by Sijar
 */
@Controller
@RequestMapping("/small")
public class BookCircleController {
    @Autowired
    private TMemberService tMemberService;
    @Autowired
    private TBookCircleService tBookCircleService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private TBcircleMemberService tBcircleMemberService;

    @ResponseBody
    @GetMapping("/bookCircle")
    public R myBookCircle(@RequestParam Map<String,Object> params){
        System.out.println("<----bookCircle       start--->");
        List<BookCircleInf> my_book_circle_list = new ArrayList<>();
        List<BookCircleInf> other_book_circle_list = new ArrayList<>();
        R r=new R();
        try {
            String openId = new String();
            for(String key : params.keySet()){
                if(key.equals("access_token")){
                    /// openId = params.get(key).toString();
                    openId = jwtUtil.getWxOpenIdByToken(params.get(key).toString());
                }
            }
            if(openId != null) {
                Map<String, Object> param = new HashMap<>();
                param.put("openId", openId);
                UserInf seMember = tMemberService.selectOne(param);
                param.clear();
                if (seMember != null) {
                    param.put("createrId", seMember.getUserId());
                    param.put("memberId",seMember.getUserId());
                    System.out.println("----------USER_ID="+param);
                    //查询所有我创建的图书圈
                    my_book_circle_list = tBookCircleService.selectbyCreaterID(param);
                    //查询所有我未加入的图书圈
                    other_book_circle_list=tBookCircleService.selectbyNotCreaterIDMemberID(param);
                    param.clear();
                }
            }
            r.put("my_book_circle_list",my_book_circle_list);
            r.put("other_book_circle_list",other_book_circle_list);
            System.out.println("--------my_book_circle_list:"+my_book_circle_list);
            System.out.println("--------other_book_circle_list:"+other_book_circle_list);
            System.out.println("<-------bookCircle  -------end>");

        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return r;
    }

    @ResponseBody
    @GetMapping("/newBookCircle")
    public R newBookCircle(@RequestParam Map<String,Object> params){
        System.out.println("<--newBookCircle  start----->");
        System.out.println("params:"+params);
        String bcName=new String();
        String intro=new String();
        Date establishTime=new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        R r=new R();
        try {
            String openId = new String();
            openId = jwtUtil.getWxOpenIdByToken(params.get("access_token").toString());
            bcName=params.get("bcName").toString();
            intro=params.get("intro").toString();
            if(openId != null) {
                Map<String, Object> param = new HashMap<>();
                param.put("openId", openId);
                UserInf seMember = tMemberService.selectOne(param);
                param.clear();
                if (seMember != null) {
                    param.put("createrId", seMember.getUserId());
                    param.put("bcName",bcName);
                    param.put("intro",intro);
                    param.put("establishTime",dateFormat.format(establishTime));
                    System.out.println("param::::::"+param);
                    r.put("result",tBookCircleService.insert(param));
                    param.clear();
                }
            }

            System.out.println("<-----newBookCircle end---->");

        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return r;
    }


    @ResponseBody
    @GetMapping("/searchBookCircleBName")
    public R searchBookCircleBName(@RequestParam Map<String,Object> params){
        System.out.println("<--searchBookCircle BName  start----->");
        System.out.println("params:"+params);
        String bName=new String();
        List<BookCircleInf> searchBCInf=new ArrayList<>();
        R r=new R();
        try {
            bName=params.get("bName").toString();
            System.out.println("bcName========="+bName);
            Map<String, Object> param = new HashMap<>();
            param.put("bName",bName);
            searchBCInf=tBookCircleService.seletbybName_bc(param);
            param.clear();
            r.put("searchBCInf",searchBCInf);
            System.out.println("<--searchBookCircle  end----->");
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }

        return r;
    }


    @ResponseBody
    @GetMapping("/searchBookCircleBCName")
    public R searchBookCircleBCName(@RequestParam Map<String,Object> params){
        System.out.println("<--searchBookCircle BCName start----->");
        System.out.println("params:"+params);
        String bcName=new String();
        List<BookCircleInf> searchBCInf=new ArrayList<>();
        R r=new R();
        try {
            bcName=params.get("bcName").toString();
            System.out.println("bcName========="+bcName);
            Map<String, Object> param = new HashMap<>();
            param.put("bcName",bcName);
            searchBCInf=tBookCircleService.selectLikBCName(param);

            param.clear();
            r.put("searchBCInf",searchBCInf);
            System.out.println("<--searchBookCircle  end----->");
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return r;
    }



    @ResponseBody
    @GetMapping("/bsecond/cMember")
    public R cMember(@RequestParam Map<String,Object> params){
        System.out.println("<--cMember start----->");
        System.out.println("params:"+params);
        String circleId=new String();
        BookCircleInf bookCircleInf=new BookCircleInf();
        R r=new R();
        try {
            circleId=params.get("circleId").toString();
            System.out.println("circleId========="+circleId);
            bookCircleInf= tBookCircleService.selectByPrimaryKey(Integer.parseInt(circleId));
            r.put("bookCircleInf",bookCircleInf);
            r.put("bcName",bookCircleInf.getBcName());
            r.put("createTime",bookCircleInf.getEstablishTime());
            r.put("intro",bookCircleInf.getIntro());
            r.put("picPath",bookCircleInf.getCirclePicPath());
            System.out.println("<--cMember end----->");
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }


        Map<String, Object> result = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        Integer bookCircleId = Integer.valueOf(circleId);
        param.put("bookCircleId", bookCircleId);
        List<rBcircleMember> mlist = new ArrayList<>();

        try {
            mlist = tBcircleMemberService.list(param);
            for (rBcircleMember rBcircleMemberTemp : mlist) {
                UserInf userInf = new UserInf();
                param.clear();
                param.put("userId",rBcircleMemberTemp.getMemberId());
                userInf = tMemberService.listPublic(param).get(0);
                rBcircleMemberTemp.setUserInf(userInf);

            }

            r.put("data", mlist);
            result.put("is_exist", 1);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("is_exist", 0);
        }

        r.put("status", result);
        return r;
    }


    @ResponseBody
    @GetMapping("/bsecond/otherCircle")
    public R otherCircle(@RequestParam Map<String,Object> params){
        System.out.println("<--otherCircle start----->");
        System.out.println("params:"+params);
        String bcName=new String();
        List<BookCircleInf> searchBCInf=new ArrayList<>();
        R r=new R();
        try {
            bcName=params.get("bcName").toString();
            System.out.println("bcName========="+bcName);
            Map<String, Object> param = new HashMap<>();
            param.put("bcName",bcName);
            searchBCInf=tBookCircleService.selectLikBCName(param);

            param.clear();
            r.put("searchBCInf",searchBCInf);
            System.out.println("<--otherCircle end----->");
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
        return r;
    }
}
