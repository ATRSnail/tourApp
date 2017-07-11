package com.tour.tourapp.entity;

import java.util.List;

/**
 * Created by ATRSnail on 2017/5/25.
 */

public class GoodsClassify {


    @Override
    public String toString() {
        return "GoodsClassify{" +
                "id=" + id +
                ", utime=" + utime +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                ", parent=" + parent +
                ", code='" + code + '\'' +
                ", ctime=" + ctime +
                ", children=" + children +
                '}';
    }

    /**
     * id : 485
     * utime : null
     * name : 童装玩具
     * seq : 4
     * children : [{"id":486,"utime":null,"name":"积木","seq":1,"children":null,"parent":485,"code":"0010","ctime":null},{"id":487,"utime":null,"name":"户外玩具","seq":2,"children":null,"parent":485,"code":"0011","ctime":null}]
     * parent : 0
     * code : children_toys
     * ctime : 1495690313000
     */



    private int id;
    private Object utime;
    private String name;
    private int seq;
    private int parent;
    private String code;
    private long ctime;
    private List<ChildrenBean> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getUtime() {
        return utime;
    }

    public void setUtime(Object utime) {
        this.utime = utime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        @Override
        public String toString() {
            return "ChildrenBean{" +
                    "id=" + id +
                    ", utime=" + utime +
                    ", name='" + name + '\'' +
                    ", seq=" + seq +
                    ", children=" + children +
                    ", parent=" + parent +
                    ", code='" + code + '\'' +
                    ", ctime=" + ctime +
                    '}';
        }

        /**
         * id : 486
         * utime : null
         * name : 积木
         * seq : 1
         * children : null
         * parent : 485
         * code : 0010
         * ctime : null
         */




        private int id;
        private Object utime;
        private String name;
        private int seq;
        private Object children;
        private int parent;
        private String code;
        private Object ctime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getUtime() {
            return utime;
        }

        public void setUtime(Object utime) {
            this.utime = utime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public Object getChildren() {
            return children;
        }

        public void setChildren(Object children) {
            this.children = children;
        }

        public int getParent() {
            return parent;
        }

        public void setParent(int parent) {
            this.parent = parent;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getCtime() {
            return ctime;
        }

        public void setCtime(Object ctime) {
            this.ctime = ctime;
        }
    }
}
