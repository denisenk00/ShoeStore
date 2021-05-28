package ua.edu.j2ee.shoestore.services;


import java.util.LinkedList;
import java.util.List;

public class PaginationService {
    private int totalItems;
    private int itemsPerPage;
    private int currentPageNumber;
    private List items;

    public PaginationService(int totalItems, int itemsPerPage, int currentPageNumber, List items) {
        this.totalItems = totalItems;
        this.itemsPerPage = itemsPerPage;
        this.currentPageNumber = currentPageNumber;
        this.items = items;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getTotalItems() {
        return totalItems;
    }


    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public List makeBatchOfItems(){
        if(totalItems <= itemsPerPage){
            return items;
        }
        List batchOfItems = new LinkedList();
        int beginIndex = itemsPerPage * (currentPageNumber - 1);
        for(int i = beginIndex; i < beginIndex + itemsPerPage || i < items.size(); i++){
            batchOfItems.add(items.get(i));
        }
        return batchOfItems;
    }


    public String makePagingLinks(String pageLocation, String addToRequest) {
        String ret = "";
        if (totalItems <= itemsPerPage)
            return ret;
        int totalPages = (totalItems / itemsPerPage);
        if (totalItems % itemsPerPage != 0)
            totalPages++;
        if(currentPageNumber > totalPages)
            currentPageNumber = 1;
        if (totalItems <= currentPageNumber * itemsPerPage)
            currentPageNumber = totalPages;
        int start = currentPageNumber - 3;
        if (start <= 0)
            start = 1;
        int end = currentPageNumber + 3;
        if (end >= totalPages)
            end = totalPages;
        if (start > 1){
            ret += "<a href='" + pageLocation + "?page=1"
                    + addToRequest + "\'>1</a> ";
        }
        if (start > 2)
            ret += "... ";
        for (int i = start; i <= end; i++) {
            if (i == currentPageNumber){
                ret += "<span style=\"font-weight: bold;" +
                        "color: red;\">" + (i) + "</span>";
            }else{
                ret += "<a href='" + pageLocation + "?page="
                        + (i) + addToRequest + "'>" + (i) + "</a>";
            }
            if (i < totalPages)
                ret += " ";
        }
        if (end + 1 < totalPages)
            ret += "... ";
        if (end < totalPages) {
            if (totalPages == currentPageNumber){
                ret += "<strong>" + totalPages + "</strong>";
            }else{
                ret += "<a href='" + pageLocation + "?page="
                        + totalPages + addToRequest + "'>"
                        + totalPages + "</a>";
            }
        }
        ret += "  ";
        return ret;
    }

}

