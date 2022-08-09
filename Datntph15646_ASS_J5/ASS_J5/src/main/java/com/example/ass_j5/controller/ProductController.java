package com.example.ass_j5.controller;

import com.example.ass_j5.entity.Account;
import com.example.ass_j5.entity.Cart;
import com.example.ass_j5.entity.Category;
import com.example.ass_j5.entity.Product;
import com.example.ass_j5.excel.ReadExcelExample;
import com.example.ass_j5.excel.WriteExcelExample;
import com.example.ass_j5.repositories.CategoryRepositories;
import com.example.ass_j5.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired
    private ProductRepositories productRepositories;
    @Autowired
    private CategoryRepositories categoryRepositories;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @GetMapping("/home")
    public String getAllProduct(ModelMap modelMap) {
        response.setContentType("text/html;charset=UTF-8");
        modelMap.addAttribute("listC", categoryRepositories.findCategoryByUpdelete(1));
        modelMap.addAttribute("listP", productRepositories.findProductByUpdelete(1));
        List<Product> list = productRepositories.findAll();
        modelMap.addAttribute("p", productRepositories.findTopByOrderByPriceDesc());
        return "Home";
    }

    //ToDo
    @GetMapping("/category/{Id}")
    public String getProductsByCategoryID(ModelMap modelMap, @PathVariable String Id) {
        response.setContentType("text/html;charset=UTF-8");
        Iterable<Product> products = productRepositories.findByCateAndUpdelete(Id,1);
//

        modelMap.addAttribute("listP", products);
        modelMap.addAttribute("listC", categoryRepositories.findCategoryByUpdelete(1));
        modelMap.addAttribute("p", productRepositories.findTopByOrderByPriceDesc());
        modelMap.addAttribute("tag", Id);
        return "Home"; //"productList.jsp"
    }

    @GetMapping("/detail/{Id}")
    public String getDetail(ModelMap modelMap, @PathVariable String Id) {
        response.setContentType("text/html;charset=UTF-8");


        Product p = productRepositories.findByIdAndUpdelete(Id, 1);
        modelMap.addAttribute("listP", productRepositories.findProductByUpdelete(1));
        modelMap.addAttribute("detail", p);
        modelMap.addAttribute("p", productRepositories.findTopByOrderByPriceDesc());
        return "Detail";
    }

    @PostMapping("/addProduct/{Id}")
    public String insertProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult //validation
            , @PathVariable String Id
    ) {
        response.setContentType("text/html;charset=UTF-8");
        modelMap.addAttribute("listC", categoryRepositories.findCategoryByUpdelete(1));

        if (bindingResult.hasErrors()) {
            return "Product";
        }
        try {
            product.setId(UUID.randomUUID().toString());
            product.setCreateDate(LocalDateTime.now());
            product.setUpDate(LocalDateTime.now());
            product.setUpdelete(1);
            product.setAccount(Id);
            productRepositories.save(product);
            return "redirect:/editProduct/" + Id;
        } catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            return "Product";
        }
    }



    @GetMapping("/editProduct/{Id}")
    public String editProduct(ModelMap modelMap, @PathVariable String Id) {
        response.setContentType("text/html;charset=UTF-8");
        modelMap.addAttribute("listC", categoryRepositories.findCategoryByUpdelete(1));
        Iterable<Product> products = productRepositories.findByAccountAndUpdelete(Id, 1);
        modelMap.addAttribute("account", Id);
        int hienthi = 0;
        request.setAttribute("hienthi", hienthi);
        modelMap.addAttribute("listP", products);
        return "Product";
    }

    @PostMapping("/updateProduct")
    public String getUpdateProduct(ModelMap modelMap,
                                   @Valid @ModelAttribute("product") Product product,
                                   BindingResult bindingResult) {
        response.setContentType("text/html;charset=UTF-8");
        modelMap.addAttribute("listC", categoryRepositories.findCategoryByUpdelete(1));
        HttpSession session = request.getSession(false);
        Account acc = new Account();
        if (session != null) {
            acc = (Account) session.getAttribute("acc");
        }

        if (bindingResult.hasErrors()) {
            return "Product";
        }
        System.out.println(product);
        try {
            if (productRepositories.findById(product.getId()).isPresent()) {
                Product foundProduct = productRepositories
                        .findById(product.getId()).get();
                if (!product.getName().trim().isEmpty()) {
                    foundProduct.setName(product.getName());
                }
                if (!product.getCate().isEmpty()) {
                    foundProduct.setCate(product.getCate());
                }
                //is empty => "" OR NULL
                if (!product.getImage().trim().isEmpty()) {
                    foundProduct.setImage(product.getImage());
                }
                if (product.getPrice() > 0) {
                    foundProduct.setPrice(product.getPrice());
                }
                if (!product.getTitle().isEmpty()) {
                    foundProduct.setTitle(product.getTitle());
                }
                foundProduct.setUpDate(LocalDateTime.now());
                System.out.println(foundProduct);
                productRepositories.save(foundProduct);

            }
        } catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            return "Product";
        }

        return "redirect:/editProduct/" + acc.getId();

    }
    @GetMapping("/delete/{Id}")
    public String getDeleteProduct(ModelMap modelMap,@PathVariable String Id) {
        response.setContentType("text/html;charset=UTF-8");
        Product product = productRepositories.findByIdAndUpdelete(Id,1);
        HttpSession session = request.getSession(false);
        Account acc = new Account();
        if (session != null) {
            acc = (Account) session.getAttribute("acc");
        }

        product.setUpdelete(0);
        product.setUpDate(LocalDateTime.now());
        productRepositories.save(product);

        return "redirect:/editProduct/" + acc.getId();

    }

    @GetMapping("/loadProduct/{Id}")
    public String getProductUpdate(ModelMap modelMap, @PathVariable String Id) {
        response.setContentType("text/html;charset=UTF-8");
        Product listP = productRepositories.findByIdAndUpdelete(Id, 1);
        List<Category> listC = categoryRepositories.findCategoryByUpdelete(1);
        modelMap.addAttribute("listC", listC);
        int hienthi = 1;
        modelMap.addAttribute("hienthi", hienthi);
        modelMap.addAttribute("product", listP);
        return "Product";
    }
    //TODO delete vĩnh viễn
    @RequestMapping(value = "/deleteProduct/{productID}", method = RequestMethod.POST)
    public String deleteProduct(ModelMap modelMap, @PathVariable String productID) {
        response.setContentType("text/html;charset=UTF-8");
        productRepositories.deleteById(productID);
        return "redirect:/categories";
    }
    //ToDO
    @RequestMapping(value = "/set/{categoryID}", method = RequestMethod.GET)
    public String setProductsByCategoryID(ModelMap modelMap, @PathVariable String categoryID) {
        response.setContentType("text/html;charset=UTF-8");
        Iterable<Product> products = productRepositories.findByCateAndUpdelete(categoryID,1);

        modelMap.addAttribute("products", products);
        return "productList"; //"productList.jsp"
    }

    @RequestMapping(value = "/changeCategory/{productID}", method = RequestMethod.GET)
    public String changeCategory(ModelMap modelMap, @PathVariable String productID) {
        response.setContentType("text/html;charset=UTF-8");
        modelMap.addAttribute("categories", categoryRepositories.findCategoryByUpdelete(1));
        modelMap.addAttribute("product", productRepositories.findById(productID).get());
        return "productList";
    }

    @GetMapping("/load")
    public void loadProduct(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        //tam thoi load ra 3 san pham truoc
        String amount = request.getParameter("exits");
        int iamount = Integer.parseInt(amount);
      List<Product> list= productRepositories.findByNext3(iamount);
        PrintWriter out = response.getWriter();

        for (Product o : list) {
            out.println("<div class=\"product col-12 col-md-6 col-lg-4\">\n"
                    + "                                <div class=\"card\">\n"
                    + "                                    <img class=\"card-img-top\" src=\""+o.getImage()+"\" alt=\"Card image cap\">\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail/"+o.getId()+"\" title=\"View Product\">"+o.getName()+"</a></h4>\n"
                    + "                                        <p class=\"card-text show_txt\">"+o.getTitle()+"</p>\n"
                    + "                                        <div class=\"row\">\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <p class=\"btn btn-danger btn-block\">"+o.getPrice()+" $</p>\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <a href=\"detail/"+o.getId()+"\" class=\"btn btn-success btn-block\">Add to cart</a>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>");
        }
    }

    //TODO
    @GetMapping("/searchtxt")
    public void autoSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        //dịch được search tiếng việt

            request.setCharacterEncoding("UTF-8");

        String txtSearch = request.getParameter("txt");
        List<Product> list = productRepositories.findByNameLikeAndUpdelete("%"+txtSearch+"%",1);
        PrintWriter out = response.getWriter();
        for (Product o : list) {

       out.println("<div class=\"product col-12 col-md-6 col-lg-4\">\n"
                    + "                                <div class=\"card\">\n"
                    + "                                    <img class=\"card-img-top\" src=\""+o.getImage()+"\" alt=\"Card image cap\">\n"
                    + "                                    <div class=\"card-body\">\n"
                    + "                                        <h4 class=\"card-title show_txt\"><a href=\"detail/"+o.getId()+"\" title=\"View Product\">"+o.getName()+"</a></h4>\n"
                    + "                                        <p class=\"card-text show_txt\">"+o.getTitle()+"</p>\n"
                    + "                                        <div class=\"row\">\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <p class=\"btn btn-danger btn-block\">"+o.getPrice()+" $</p>\n"
                    + "                                            </div>\n"
                    + "                                            <div class=\"col\">\n"
                    + "                                                <a href=\"detail/"+o.getId()+"\" class=\"btn btn-success btn-block\">Add to cart</a>\n"
                    + "                                            </div>\n"
                    + "                                        </div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>");
        }


    }
    //TODO
    @GetMapping("/print")
    public String showCart(ModelMap modelMap) throws IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        Cookie[] arr = request.getCookies();
//        PrintWriter out = response.getWriter();
//        List<Cart> list = new ArrayList<>();
//        for (Cookie o : arr) {
//            if (o.getName().equals("id")) {
//                String[] txt = o.getValue().split(",");
//                for (String s : txt) {
//                  productRepositories.findById(s);
//                }
//            }
//        }
//        for (int i = 0; i < list.size(); i++) {
//            int count = 1;
//            for (int j = i+1; j < list.size(); j++) {
//                if(list.get(i).getProduct().getId() == list.get(j).getProduct().getId()){
//                    count++;
//                    list.remove(j);
//                    j--;
//                    list.get(i).setAmount(count);
//                }
//            }
//        }
//        double total = 0;
//        for (Cart o : list) {
//            total = total + o.getAmount() * o.getProduct().getPrice();
//        }
//        modelMap.addAttribute("list", list);
//        modelMap.addAttribute("total", total);
//        modelMap.addAttribute("vat", 0.1 * total);
//        modelMap.addAttribute("sum", 1.1 * total);
        return "Cart";

    }
    @GetMapping("/remove/{Id}")
    public String removeCart(ModelMap modelMap,@PathVariable String Id){
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] arr = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        String[] ids = txt.split(",");
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (!ids[i].equals(Id)) {
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "," + ids[i];
                }
            }
        }
        if (!txtOutPut.isEmpty()) {
            Cookie c = new Cookie("Id", txtOutPut);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
        }
        return "redirect:/print";
    }
    @GetMapping("/order")
    public String OrderControl(ModelMap modelMap){
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] arr = request.getCookies();
        List<Cart> list = new ArrayList<>();
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                String[] txt = o.getValue().split(",");
                for (String s : txt) {
                productRepositories.findById(s);
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            int count = 1;
            for (int j = i+1; j < list.size(); j++) {
                if(list.get(i).getProduct().getId() == list.get(j).getProduct().getId()){
                    count++;
                    list.remove(j);
                    j--;
                    list.get(i).setAmount(count);
                }
            }
        }
        for (Cookie o : arr) {
            o.setMaxAge(0);
            response.addCookie(o);
        }
        return "redirect:/home";
    }
    @GetMapping("/cart{Id}")
    public String cartControl(ModelMap modelMap,@PathVariable String Id){
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] arr = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        if (txt.isEmpty()) {
            txt = Id;
        } else {
            txt = txt + "," + Id;
        }
        Cookie c = new Cookie("id", txt);
        c.setMaxAge(60 * 60 * 24);
        response.addCookie(c);
        return "print";
    }
    @GetMapping("/sub/{Id}")
    public String subControll(ModelMap modelMap,@PathVariable String Id){
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] arr = request.getCookies();
        String txt = "";
        for (Cookie o : arr) {
            if (o.getName().equals("id")) {
                txt = txt + o.getValue();
                o.setMaxAge(0);
                response.addCookie(o);
            }
        }
        String[] ids = txt.split(",");
        String txtOutPut = "";
        int check = 0;
        for (int i = 0; i < ids.length; i++) {
            if (ids[i].equals(Id)) {
                check++;
            }
            if (check != 1) {
                if (txtOutPut.isEmpty()) {
                    txtOutPut = ids[i];
                } else {
                    txtOutPut = txtOutPut + "," + ids[i];
                }
            } else {
                check++;
            }
        }
        if (!txtOutPut.isEmpty()) {
            Cookie c = new Cookie("id", txtOutPut);
            c.setMaxAge(60 * 60 * 24);
            response.addCookie(c);
        }
        return "print";
    }
    @GetMapping("/product/writeExcel")
    public String writeExcel(ModelMap modelMap) throws IOException {
        final List<Product> books = WriteExcelExample.getBooks();
        final String excelFilePath = "products.xlsx";
        WriteExcelExample.writeExcel(books, excelFilePath);
        System.out.println("đã in");
        return "redirect:/categories";
    }
    @GetMapping("/product/readExcel")
    public String readExcel(ModelMap modelMap) throws IOException {
        final String excelFilePath = "products.xlsx";
        final List<Product> books = new ReadExcelExample().readExcel(excelFilePath);
        productRepositories.deleteAll();
        productRepositories.saveAll(books);
        System.out.println("đã cập nhật");
        return "redirect:/categories";
    }
    @GetMapping("/products/{Id}")
    public String getProductByCate(ModelMap modelMap, @PathVariable String Id){
        Iterable<Product> products = productRepositories.findByCate(Id);
        modelMap.addAttribute("products", products);
        return "productList";
    }
    @GetMapping("/products/changeCategory/{productID}")
    public String changeCategoryupdate(ModelMap modelMap, @PathVariable String productID) {
        modelMap.addAttribute("categories", categoryRepositories.findAll());
        modelMap.addAttribute("product", productRepositories.findById(productID).get());
        return "updateProduct";
    }
    @PostMapping("/products/deleteProduct/{productID}")
    public String deleteProductbycategory(ModelMap modelMap, @PathVariable String productID) {
        boolean exist = productRepositories.existsById(productID);
        if (exist) {
            productRepositories.deleteById(productID);
        }
        return "redirect:/categories";
    }
    @PostMapping( "/products/updateProduct/{productID}")
    public String updateProduct(ModelMap modelMap,
                                @Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult,
                                @PathVariable String productID
    ) {
        Iterable<Category> categories = categoryRepositories.findAll();
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("categories", categories);
            return "updateProduct";
        }
        try {
            if (productRepositories.findById(productID).isPresent()) {
                Product foundProduct = productRepositories
                        .findById(productID).get();
                if (!product.getName().trim().isEmpty()) {
                    foundProduct.setName(product.getName());
                }
                if (!product.getCate().isEmpty()) {
                    foundProduct.setCate(product.getCate());
                }
                //is empty => "" OR NULL
                if (!product.getTitle().trim().isEmpty()) {
                    foundProduct.setTitle(product.getTitle());
                }
                if (!product.getImage().trim().isEmpty()) {
                    foundProduct.setImage(product.getImage());
                }
                if (product.getPrice() > 0) {
                    foundProduct.setPrice(product.getPrice());
                }
                foundProduct.setUpDate(LocalDateTime.now());
                foundProduct.setUpdelete(product.getUpdelete());
                productRepositories.save(foundProduct);
            }
        } catch (Exception e) {
            modelMap.addAttribute("error", e.toString());
            modelMap.addAttribute("categories", categories);
            return "updateProduct";
        }
        return "redirect:/products/" + product.getCate();
    }
}
