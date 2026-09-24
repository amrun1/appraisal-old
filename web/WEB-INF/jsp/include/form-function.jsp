<%!
    public String formDate(String name){
        return "<div class=\"control-group span6\">\n" +
                "<label class=\"control-label\" for=\""+name+"\">Tanggal Order Ke Eksternal</label>\n" +
                "<div class=\"controls\">\n" +
                "<f:input path=\""+name+"\" data-date-format=\"dd-mm-yyyy\"" +
                " data-provide=\"datepicker\" placeholder=\"DD-MM-YYYY\" data-date-end-date=\"0d\"/>\n" +
                "<f:errors path=\""+name+"\" cssClass=\"help-block error has-error\"/>\n" +
                "</div>\n" +
                "</div>\n";
    }
%>