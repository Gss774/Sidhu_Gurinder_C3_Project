import java.sql.Array;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

//import static javax.swing.text.rtf.RTFAttributes.BooleanAttribute.False;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();


    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        LocalTime CurrentTime = getCurrentTime();
        int open = CurrentTime.compareTo(openingTime);
        int close = CurrentTime.compareTo(closingTime);
        if((open>=0) && (close<=0)){
            return true;
        }
        return false;
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;

    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int totalOrder(List<String> orderItems) throws itemNotFoundException {
        int total = 0;
        for(String item: orderItems){
            int menuPrice = -1;
            for(Item menuItem: menu) {
                if(item.equals(menuItem.getName())){
                    menuPrice = menuItem.getPrice();
                }
            }
            if(menuPrice == -1){
                throw new itemNotFoundException(item);
            }
            else{
                total += menuPrice;
            }
        }
        return total;
    }

}
