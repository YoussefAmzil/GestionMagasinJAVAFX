package model;

public enum PaymentT {
    CHEQUE{
        String id="";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    } ,CARTE{
        String id="";
        String date_exp="";

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate_exp() {
            return date_exp;
        }

        public void setDate_exp(String date_exp) {
            this.date_exp = date_exp;
        }
    } ,ESPECE
}
