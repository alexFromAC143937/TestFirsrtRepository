package HW_2023014;

public enum SingleGameResult {
    LOSE(0),
    DRAW(0.5),
    WIN(1);

     double result; /// ToDo Question : Should it be private or not. getResult did not work ( it presents but does not work)?
    SingleGameResult(double result) {
        this.result = result;
    }

    public void setResult(double result){
        this.result = result;
    }

    public double getResult(){
        return result;
    }
}