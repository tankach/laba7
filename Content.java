package sumdu.edu.ua.webstudent.res;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author tanka
 */
public class Content {
    
    private int id;
    private int stud_Id;
    private String title;
    private String makr_let;
    private int makr;

    public Content(int id, int stud_Id,String title, String makr_let,int makr) {
        this.id = id;
        this.stud_Id = stud_Id;
        this.title = title;
        this.makr_let = makr_let;
        this.makr = makr;
    }

    // Геттери і сеттери тут

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return stud_Id;
    }

    public String getTitle() {
        return title;
    }

    public int getMakr() {
        return makr;
    }

    public String getMakr_let() {
        return makr_let;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setStudentId(int stud_Id) {
        this.stud_Id = stud_Id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMakr_let(String makr_let) {
        this.makr_let = makr_let;
    }

    public void setMakr(int makr) {
        this.makr = makr;
    }

}

