package com.mvc44;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreController {
    private List<Store> stores = new ArrayList<>();

    @GetMapping("/stores")
    public String getStores(Model model) {
        model.addAttribute("stores", stores);
        return "storeList";
    }

    @GetMapping("/stores/{index}")
    public String getStore(@PathVariable("index") int index, Model model) {
        Store store = stores.get(index);
        model.addAttribute("store", store);
        return "storeDetails";
    }

    @GetMapping("/stores/add")
    public String showAddStoreForm(Model model) {
        model.addAttribute("store", new Store());
        return "addStoreForm";
    }

    @PostMapping("/stores/add")
    public String addStore(@ModelAttribute("store") Store store) {
        stores.add(store);
        return "redirect:/stores";
    }
    @GetMapping("/stores/{index}/edit")
    public String showEditStoreForm(@PathVariable("index") int index, Model model) {
        Store store = stores.get(index);
        model.addAttribute("store", store);
        return "editStoreForm";
    }

    @PostMapping("/stores/{index}/edit")
    public String editStore(@PathVariable("index") int index, @ModelAttribute("store") Store updatedStore) {
        Store store = stores.get(index);
        store.setName(updatedStore.getName());
        store.setAddress(updatedStore.getAddress());
        store.setPhone(updatedStore.getPhone());
        store.setEmail(updatedStore.getEmail());
        store.setWebsite(updatedStore.getWebsite());
        store.setCategory(updatedStore.getCategory());
        store.setDescription(updatedStore.getDescription());
        return "redirect:/stores";
    }

    @GetMapping("/stores/{index}/delete")
    public String deleteStore(@PathVariable("index") int index) {
        stores.remove(index);
        return "redirect:/stores";
    }

    @GetMapping("/stores/search")
    public String searchStores(@RequestParam("query") String query, Model model) {
        List<Store> searchResults = new ArrayList<>();
        for (Store store : stores) {
            if (store.getName().contains(query) || store.getCategory().contains(query) || store.getAddress().contains(query)) {
                searchResults.add(store);
            }
        }
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }


}

