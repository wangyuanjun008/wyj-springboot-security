/*package com.wyj.controller.login;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.wyj.annotation.SysLog;
import com.wyj.entity.Retval;
import com.wyj.entity.system.Menu;
import com.wyj.service.system.MenuService;
import com.wyj.utils.ShiroUtils;

*//**
 * 登录
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年11月22日 下午8:25:35
 *//*
@Controller
public class LoginController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private Producer producer;

    @RequestMapping("/index")
    public String index() {
        return "login";
    }

    *//**
     * 验证码
     * 
     * @param response
     * @throws ServletException
     * @throws IOException
     *//*
    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
         Expires过时期限值，指浏览器或缓存服务器在该时间点后必须从真正的服务器中获取新的页面信息 
        response.setDateHeader("Expires", 0);
         浏览器和缓存服务器都不应该缓存页面信息 
        // response.setHeader("Cache-Control", "no-cache");
         请求和响应的信息都不应该被存储在对方的磁盘系统中 
        // response.setHeader("Cache-Control", "no-store");
         浏览器和缓存服务器都可以缓存页面信息 
        // response.setHeader("Cache-Control", "public");

         请求和响应的信息都不应该被存储在对方的磁盘系统中 
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

         不让浏览器或中间缓存服务器缓存页面，配合Expires 置为0限定更保险 
        response.setHeader("Pragma", "no-cache");

        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        
         * response.setContentType(MIME)的作用是使客户端浏览器区分不同类型的数据， 并根据不同的MIME调用浏览器内部不同的程序嵌入模块来处理相应的数据
         
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping("/public")
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView("public");
        List<Menu> menus = menuService.listAllMenuIdByUserId(ShiroUtils.getUserId());
        modelAndView.addObject("menus", menus);
        modelAndView.addObject("userName", ShiroUtils.getUserEntity().getName());
        return modelAndView;
    }

    *//**
     * 登录
     * 
     * @param user
     * @return
     *//*
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Retval login(String userName, String password, String captcha, HttpServletRequest request) {
        Retval retval = Retval.newInstance();
        String kaptcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equals(captcha)) {
            retval.fail("验证码不正确");
            return retval;
        }

        Subject subject = ShiroUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            subject.login(token);

        } catch (UnknownAccountException e) {
            retval.fail(e.getMessage());
            return retval;
        } catch (IncorrectCredentialsException e) {
            retval.fail(e.getMessage());
            return retval;
        } catch (LockedAccountException e) {
            retval.fail(e.getMessage());
            return retval;
        } catch (AuthenticationException e) {
            retval.fail("账户验证失败");
            return retval;
        }

        return retval;
    }

    *//**
     * 退出
     *//*
    @SysLog(action="退出登录")
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Retval logout() {
        ShiroUtils.logout();
        return Retval.newInstance() ;
    }
    
}
*/