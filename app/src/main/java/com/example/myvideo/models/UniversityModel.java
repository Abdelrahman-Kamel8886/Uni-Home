package com.example.myvideo.models;

import java.util.List;

public class UniversityModel {


    private List<Grades> Grades;
    private String address;
    private String departments;
    private String gradesno;
    private String intro;
    private String name;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Grades> getGrades() {
        return Grades;
    }

    public void setGrades(List<Grades> grades) {
        this.Grades = grades;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getGradesno() {
        return gradesno;
    }

    public void setGradesno(String gradesno) {
        this.gradesno = gradesno;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Grades {
        private List<Departments> departments;
        private String name;

        public List<Departments> getDepartments() {
            return departments;
        }

        public void setDepartments(List<Departments> departments) {
            this.departments = departments;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static class Departments {
            private String name;
            private List<Terms> terms;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Terms> getTerms() {
                return terms;
            }

            public void setTerms(List<Terms> terms) {
                this.terms = terms;
            }

            public static class Terms {
                private List<CourseModel> Courses;
                private String name;

                public List<CourseModel> getCourses() {
                    return Courses;
                }

                public void setCourses(List<CourseModel> courses) {
                    this.Courses = courses;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }


            }
        }
    }
}
