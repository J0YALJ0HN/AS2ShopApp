package com.example.as2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopFragment extends Fragment {

    private RecyclerView rvElectronics;
    private RecyclerView rvFashion;
    private RecyclerView rvHome;
    private RecyclerView rvHealth;
    private RecyclerView rvSports;
    private RecyclerView rvStationery;
    private RecyclerView rvToys;
    private RecyclerView rvAutomotive;
    private RecyclerView rvGroceries;
    private RecyclerView rvMisc;

    private ShopAdapter electronicsAdapter;
    private ShopAdapter fashionAdapter;
    private ShopAdapter homeAdapter;
    private ShopAdapter healthAdapter;
    private ShopAdapter sportsAdapter;
    private ShopAdapter stationeryAdapter;
    private ShopAdapter toysAdapter;
    private ShopAdapter automotiveAdapter;
    private ShopAdapter groceriesAdapter;
    private ShopAdapter miscellaneousAdapter;

    private ArrayList<String> electronicsProdNameList, electronicsProdDescList, electronicsCategoryList, electronicsProdListPriceList, electronicsProdRetailPriceList, electronicsProdQuantityList, electronicsProdDateCreatedList;
    private ArrayList<String> fashionProdNameList, fashionProdDescList, fashionCategoryList, fashionProdListPriceList, fashionProdRetailPriceList, fashionProdQuantityList, fashionProdDateCreatedList;
    private ArrayList<String> homeProdNameList, homeProdDescList, homeCategoryList, homeProdListPriceList, homeProdRetailPriceList, homeProdQuantityList, homeProdDateCreatedList;
    private ArrayList<String> healthProdNameList, healthProdDescList, healthCategoryList, healthProdListPriceList, healthProdRetailPriceList, healthProdQuantityList, healthProdDateCreatedList;
    private ArrayList<String> sportsProdNameList, sportsProdDescList, sportsCategoryList, sportsProdListPriceList, sportsProdRetailPriceList, sportsProdQuantityList, sportsProdDateCreatedList;
    private ArrayList<String> stationeryProdNameList, stationeryProdDescList, stationeryCategoryList, stationeryProdListPriceList, stationeryProdRetailPriceList, stationeryProdQuantityList, stationeryProdDateCreatedList;

    private ArrayList<String> toysProdNameList, toysProdDescList, toysCategoryList, toysProdListPriceList, toysProdRetailPriceList, toysProdQuantityList, toysProdDateCreatedList;
    private ArrayList<String> automotiveProdNameList, automotiveProdDescList, automotiveCategoryList, automotiveProdListPriceList, automotiveProdRetailPriceList, automotiveProdQuantityList, automotiveProdDateCreatedList;
    private ArrayList<String> groceriesProdNameList, groceriesProdDescList, groceriesCategoryList, groceriesProdListPriceList, groceriesProdRetailPriceList, groceriesProdQuantityList, groceriesProdDateCreatedList;

    private ArrayList<String> miscellaneousProdNameList, miscellaneousProdDescList, miscellaneousCategoryList, miscellaneousProdListPriceList, miscellaneousProdRetailPriceList, miscellaneousProdQuantityList, miscellaneousProdDateCreatedList;

    private DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        dbHelper = new DBHelper(getActivity());

        rvElectronics = view.findViewById(R.id.rvElectro);
        rvElectronics.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvFashion = view.findViewById(R.id.rvFashion);
        rvFashion.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvHome = view.findViewById(R.id.rvHome);
        rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvHealth = view.findViewById(R.id.rvHealth);
        rvHealth.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvSports = view.findViewById(R.id.rvSports);
        rvSports.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvStationery = view.findViewById(R.id.rvStationary);
        rvStationery.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvToys = view.findViewById(R.id.rvToys);
        rvToys.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvAutomotive = view.findViewById(R.id.rvAutomotive);
        rvAutomotive.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvGroceries = view.findViewById(R.id.rvGroceries);
        rvGroceries.setLayoutManager(new LinearLayoutManager(getActivity()));

        rvMisc = view.findViewById(R.id.rvMisc);
        rvMisc.setLayoutManager(new LinearLayoutManager(getActivity()));

        electronicsProdNameList = new ArrayList<>();
        electronicsProdDescList = new ArrayList<>();
        electronicsCategoryList = new ArrayList<>();
        electronicsProdListPriceList = new ArrayList<>();
        electronicsProdRetailPriceList = new ArrayList<>();
        electronicsProdQuantityList = new ArrayList<>();
        electronicsProdDateCreatedList = new ArrayList<>();

        fashionProdNameList = new ArrayList<>();
        fashionProdDescList = new ArrayList<>();
        fashionCategoryList = new ArrayList<>();
        fashionProdListPriceList = new ArrayList<>();
        fashionProdRetailPriceList = new ArrayList<>();
        fashionProdQuantityList = new ArrayList<>();
        fashionProdDateCreatedList = new ArrayList<>();

        homeProdNameList = new ArrayList<>();
        homeProdDescList = new ArrayList<>();
        homeCategoryList = new ArrayList<>();
        homeProdListPriceList = new ArrayList<>();
        homeProdRetailPriceList = new ArrayList<>();
        homeProdQuantityList = new ArrayList<>();
        homeProdDateCreatedList = new ArrayList<>();

        healthProdNameList = new ArrayList<>();
        healthProdDescList = new ArrayList<>();
        healthCategoryList = new ArrayList<>();
        healthProdListPriceList = new ArrayList<>();
        healthProdRetailPriceList = new ArrayList<>();
        healthProdQuantityList = new ArrayList<>();
        healthProdDateCreatedList = new ArrayList<>();

        sportsProdNameList = new ArrayList<>();
        sportsProdDescList = new ArrayList<>();
        sportsCategoryList = new ArrayList<>();
        sportsProdListPriceList = new ArrayList<>();
        sportsProdRetailPriceList = new ArrayList<>();
        sportsProdQuantityList = new ArrayList<>();
        sportsProdDateCreatedList = new ArrayList<>();

        stationeryProdNameList = new ArrayList<>();
        stationeryProdDescList = new ArrayList<>();
        stationeryCategoryList = new ArrayList<>();
        stationeryProdListPriceList = new ArrayList<>();
        stationeryProdRetailPriceList = new ArrayList<>();
        stationeryProdQuantityList = new ArrayList<>();
        stationeryProdDateCreatedList = new ArrayList<>();

        toysProdNameList = new ArrayList<>();
        toysProdDescList = new ArrayList<>();
        toysCategoryList = new ArrayList<>();
        toysProdListPriceList = new ArrayList<>();
        toysProdRetailPriceList = new ArrayList<>();
        toysProdQuantityList = new ArrayList<>();
        toysProdDateCreatedList = new ArrayList<>();

        automotiveProdNameList = new ArrayList<>();
        automotiveProdDescList = new ArrayList<>();
        automotiveCategoryList = new ArrayList<>();
        automotiveProdListPriceList = new ArrayList<>();
        automotiveProdRetailPriceList = new ArrayList<>();
        automotiveProdQuantityList = new ArrayList<>();
        automotiveProdDateCreatedList = new ArrayList<>();

        groceriesProdNameList = new ArrayList<>();
        groceriesProdDescList = new ArrayList<>();
        groceriesCategoryList = new ArrayList<>();
        groceriesProdListPriceList = new ArrayList<>();
        groceriesProdRetailPriceList = new ArrayList<>();
        groceriesProdQuantityList = new ArrayList<>();
        groceriesProdDateCreatedList = new ArrayList<>();

        miscellaneousProdNameList = new ArrayList<>();
        miscellaneousProdDescList = new ArrayList<>();
        miscellaneousCategoryList = new ArrayList<>();
        miscellaneousProdListPriceList = new ArrayList<>();
        miscellaneousProdRetailPriceList = new ArrayList<>();
        miscellaneousProdQuantityList = new ArrayList<>();
        miscellaneousProdDateCreatedList = new ArrayList<>();

        // Query and retrieve electronics products from the database
        Cursor electronicsCursor = dbHelper.getElectronicsData();
        if (electronicsCursor != null) {
            int prodNameIndex = electronicsCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = electronicsCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = electronicsCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = electronicsCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = electronicsCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = electronicsCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = electronicsCursor.getColumnIndexOrThrow("prodDateCreated");

            while (electronicsCursor.moveToNext()) {
                electronicsProdNameList.add(electronicsCursor.getString(prodNameIndex));
                electronicsProdDescList.add(electronicsCursor.getString(prodDescIndex));
                electronicsCategoryList.add(electronicsCursor.getString(categoryIndex));
                electronicsProdListPriceList.add(electronicsCursor.getString(prodListPriceIndex));
                electronicsProdRetailPriceList.add(electronicsCursor.getString(prodRetailPriceIndex));
                electronicsProdQuantityList.add(electronicsCursor.getString(prodQuantityIndex));
                electronicsProdDateCreatedList.add(electronicsCursor.getString(prodDateCreatedIndex));
            }

            electronicsCursor.close();
        }

        // Query and retrieve fashion products from the database
        Cursor fashionCursor = dbHelper.getFashionData();
        if (fashionCursor != null) {
            int prodNameIndex = fashionCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = fashionCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = fashionCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = fashionCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = fashionCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = fashionCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = fashionCursor.getColumnIndexOrThrow("prodDateCreated");

            while (fashionCursor.moveToNext()) {
                fashionProdNameList.add(fashionCursor.getString(prodNameIndex));
                fashionProdDescList.add(fashionCursor.getString(prodDescIndex));
                fashionCategoryList.add(fashionCursor.getString(categoryIndex));
                fashionProdListPriceList.add(fashionCursor.getString(prodListPriceIndex));
                fashionProdRetailPriceList.add(fashionCursor.getString(prodRetailPriceIndex));
                fashionProdQuantityList.add(fashionCursor.getString(prodQuantityIndex));
                fashionProdDateCreatedList.add(fashionCursor.getString(prodDateCreatedIndex));
            }

            fashionCursor.close();
        }

        // Query and retrieve home products from the database
        Cursor homeCursor = dbHelper.getHomeData();
        if (homeCursor != null) {
            int prodNameIndex = homeCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = homeCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = homeCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = homeCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = homeCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = homeCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = homeCursor.getColumnIndexOrThrow("prodDateCreated");

            while (homeCursor.moveToNext()) {
                homeProdNameList.add(homeCursor.getString(prodNameIndex));
                homeProdDescList.add(homeCursor.getString(prodDescIndex));
                homeCategoryList.add(homeCursor.getString(categoryIndex));
                homeProdListPriceList.add(homeCursor.getString(prodListPriceIndex));
                homeProdRetailPriceList.add(homeCursor.getString(prodRetailPriceIndex));
                homeProdQuantityList.add(homeCursor.getString(prodQuantityIndex));
                homeProdDateCreatedList.add(homeCursor.getString(prodDateCreatedIndex));
            }

            homeCursor.close();
        }

        // Query and retrieve health products from the database
        Cursor healthCursor = dbHelper.getHealthData();
        if (healthCursor != null) {
            int prodNameIndex = healthCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = healthCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = healthCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = healthCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = healthCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = healthCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = healthCursor.getColumnIndexOrThrow("prodDateCreated");

            while (healthCursor.moveToNext()) {
                healthProdNameList.add(healthCursor.getString(prodNameIndex));
                healthProdDescList.add(healthCursor.getString(prodDescIndex));
                healthCategoryList.add(healthCursor.getString(categoryIndex));
                healthProdListPriceList.add(healthCursor.getString(prodListPriceIndex));
                healthProdRetailPriceList.add(healthCursor.getString(prodRetailPriceIndex));
                healthProdQuantityList.add(healthCursor.getString(prodQuantityIndex));
                healthProdDateCreatedList.add(healthCursor.getString(prodDateCreatedIndex));
            }

            healthCursor.close();
        }

        // Query and retrieve sports products from the database
        Cursor sportsCursor = dbHelper.getSportsData();
        if (sportsCursor != null) {
            int prodNameIndex = sportsCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = sportsCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = sportsCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = sportsCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = sportsCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = sportsCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = sportsCursor.getColumnIndexOrThrow("prodDateCreated");

            while (sportsCursor.moveToNext()) {
                sportsProdNameList.add(sportsCursor.getString(prodNameIndex));
                sportsProdDescList.add(sportsCursor.getString(prodDescIndex));
                sportsCategoryList.add(sportsCursor.getString(categoryIndex));
                sportsProdListPriceList.add(sportsCursor.getString(prodListPriceIndex));
                sportsProdRetailPriceList.add(sportsCursor.getString(prodRetailPriceIndex));
                sportsProdQuantityList.add(sportsCursor.getString(prodQuantityIndex));
                sportsProdDateCreatedList.add(sportsCursor.getString(prodDateCreatedIndex));
            }

            sportsCursor.close();
        }

        // Query and retrieve stationery products from the database
        Cursor stationeryCursor = dbHelper.getStationeryData();
        if (stationeryCursor != null) {
            int prodNameIndex = stationeryCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = stationeryCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = stationeryCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = stationeryCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = stationeryCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = stationeryCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = stationeryCursor.getColumnIndexOrThrow("prodDateCreated");

            while (stationeryCursor.moveToNext()) {
                stationeryProdNameList.add(stationeryCursor.getString(prodNameIndex));
                stationeryProdDescList.add(stationeryCursor.getString(prodDescIndex));
                stationeryCategoryList.add(stationeryCursor.getString(categoryIndex));
                stationeryProdListPriceList.add(stationeryCursor.getString(prodListPriceIndex));
                stationeryProdRetailPriceList.add(stationeryCursor.getString(prodRetailPriceIndex));
                stationeryProdQuantityList.add(stationeryCursor.getString(prodQuantityIndex));
                stationeryProdDateCreatedList.add(stationeryCursor.getString(prodDateCreatedIndex));
            }

            stationeryCursor.close();
        }

        // Query and retrieve toy products from the database
        Cursor toysCursor = dbHelper.getToysData();
        if (toysCursor != null) {
            int prodNameIndex = toysCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = toysCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = toysCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = toysCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = toysCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = toysCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = toysCursor.getColumnIndexOrThrow("prodDateCreated");

            while (toysCursor.moveToNext()) {
                toysProdNameList.add(toysCursor.getString(prodNameIndex));
                toysProdDescList.add(toysCursor.getString(prodDescIndex));
                toysCategoryList.add(toysCursor.getString(categoryIndex));
                toysProdListPriceList.add(toysCursor.getString(prodListPriceIndex));
                toysProdRetailPriceList.add(toysCursor.getString(prodRetailPriceIndex));
                toysProdQuantityList.add(toysCursor.getString(prodQuantityIndex));
                toysProdDateCreatedList.add(toysCursor.getString(prodDateCreatedIndex));
            }

            toysCursor.close();
        }

        // Query and retrieve automotive products from the database
        Cursor automotiveCursor = dbHelper.getAutomotiveData();
        if (automotiveCursor != null) {
            int prodNameIndex = automotiveCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = automotiveCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = automotiveCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = automotiveCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = automotiveCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = automotiveCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = automotiveCursor.getColumnIndexOrThrow("prodDateCreated");

            while (automotiveCursor.moveToNext()) {
                automotiveProdNameList.add(automotiveCursor.getString(prodNameIndex));
                automotiveProdDescList.add(automotiveCursor.getString(prodDescIndex));
                automotiveCategoryList.add(automotiveCursor.getString(categoryIndex));
                automotiveProdListPriceList.add(automotiveCursor.getString(prodListPriceIndex));
                automotiveProdRetailPriceList.add(automotiveCursor.getString(prodRetailPriceIndex));
                automotiveProdQuantityList.add(automotiveCursor.getString(prodQuantityIndex));
                automotiveProdDateCreatedList.add(automotiveCursor.getString(prodDateCreatedIndex));
            }

            automotiveCursor.close();
        }

        // Query and retrieve grocery products from the database
        Cursor groceriesCursor = dbHelper.getGroceriesData();
        if (groceriesCursor != null) {
            int prodNameIndex = groceriesCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = groceriesCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = groceriesCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = groceriesCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = groceriesCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = groceriesCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = groceriesCursor.getColumnIndexOrThrow("prodDateCreated");

            while (groceriesCursor.moveToNext()) {
                groceriesProdNameList.add(groceriesCursor.getString(prodNameIndex));
                groceriesProdDescList.add(groceriesCursor.getString(prodDescIndex));
                groceriesCategoryList.add(groceriesCursor.getString(categoryIndex));
                groceriesProdListPriceList.add(groceriesCursor.getString(prodListPriceIndex));
                groceriesProdRetailPriceList.add(groceriesCursor.getString(prodRetailPriceIndex));
                groceriesProdQuantityList.add(groceriesCursor.getString(prodQuantityIndex));
                groceriesProdDateCreatedList.add(groceriesCursor.getString(prodDateCreatedIndex));
            }

            groceriesCursor.close();
        }

        // Query and retrieve miscellaneous products from the database
        Cursor miscCursor = dbHelper.getMiscellaneousData();
        if (miscCursor != null) {
            int prodNameIndex = miscCursor.getColumnIndexOrThrow("prodName");
            int prodDescIndex = miscCursor.getColumnIndexOrThrow("prodDesc");
            int categoryIndex = miscCursor.getColumnIndexOrThrow("category");
            int prodListPriceIndex = miscCursor.getColumnIndexOrThrow("prodListPrice");
            int prodRetailPriceIndex = miscCursor.getColumnIndexOrThrow("prodRetailPrice");
            int prodQuantityIndex = miscCursor.getColumnIndexOrThrow("prodQuantity");
            int prodDateCreatedIndex = miscCursor.getColumnIndexOrThrow("prodDateCreated");

            while (miscCursor.moveToNext()) {
                miscellaneousProdNameList.add(miscCursor.getString(prodNameIndex));
                miscellaneousProdDescList.add(miscCursor.getString(prodDescIndex));
                miscellaneousCategoryList.add(miscCursor.getString(categoryIndex));
                miscellaneousProdListPriceList.add(miscCursor.getString(prodListPriceIndex));
                miscellaneousProdRetailPriceList.add(miscCursor.getString(prodRetailPriceIndex));
                miscellaneousProdQuantityList.add(miscCursor.getString(prodQuantityIndex));
                miscellaneousProdDateCreatedList.add(miscCursor.getString(prodDateCreatedIndex));
            }

            miscCursor.close();
        }

        electronicsAdapter = new ShopAdapter(getActivity(), electronicsProdNameList, electronicsProdDescList, electronicsCategoryList, electronicsProdListPriceList, electronicsProdRetailPriceList, electronicsProdQuantityList, electronicsProdDateCreatedList);
        rvElectronics.setAdapter(electronicsAdapter);

        fashionAdapter = new ShopAdapter(getActivity(), fashionProdNameList, fashionProdDescList, fashionCategoryList, fashionProdListPriceList, fashionProdRetailPriceList, fashionProdQuantityList, fashionProdDateCreatedList);
        rvFashion.setAdapter(fashionAdapter);

        homeAdapter = new ShopAdapter(getActivity(), homeProdNameList, homeProdDescList, homeCategoryList, homeProdListPriceList, homeProdRetailPriceList, homeProdQuantityList, homeProdDateCreatedList);
        rvHome.setAdapter(homeAdapter);

        healthAdapter = new ShopAdapter(getActivity(), healthProdNameList, healthProdDescList, healthCategoryList, healthProdListPriceList, healthProdRetailPriceList, healthProdQuantityList, healthProdDateCreatedList);
        rvHealth.setAdapter(healthAdapter);

        sportsAdapter = new ShopAdapter(getActivity(), sportsProdNameList, sportsProdDescList, sportsCategoryList, sportsProdListPriceList, sportsProdRetailPriceList, sportsProdQuantityList, sportsProdDateCreatedList);
        rvSports.setAdapter(sportsAdapter);

        stationeryAdapter = new ShopAdapter(getActivity(), stationeryProdNameList, stationeryProdDescList, stationeryCategoryList, stationeryProdListPriceList, stationeryProdRetailPriceList, stationeryProdQuantityList, stationeryProdDateCreatedList);
        rvStationery.setAdapter(stationeryAdapter);

        toysAdapter = new ShopAdapter(getActivity(), toysProdNameList, toysProdDescList, toysCategoryList, toysProdListPriceList, toysProdRetailPriceList, toysProdQuantityList, toysProdDateCreatedList);
        rvToys.setAdapter(toysAdapter);

        automotiveAdapter = new ShopAdapter(getActivity(), automotiveProdNameList, automotiveProdDescList, automotiveCategoryList, automotiveProdListPriceList, automotiveProdRetailPriceList, automotiveProdQuantityList, automotiveProdDateCreatedList);
        rvAutomotive.setAdapter(automotiveAdapter);

        groceriesAdapter = new ShopAdapter(getActivity(), groceriesProdNameList, groceriesProdDescList, groceriesCategoryList, groceriesProdListPriceList, groceriesProdRetailPriceList, groceriesProdQuantityList, groceriesProdDateCreatedList);
        rvGroceries.setAdapter(groceriesAdapter);

        miscellaneousAdapter = new ShopAdapter(getActivity(), miscellaneousProdNameList, miscellaneousProdDescList, miscellaneousCategoryList, miscellaneousProdListPriceList, miscellaneousProdRetailPriceList, miscellaneousProdQuantityList, miscellaneousProdDateCreatedList);
        rvMisc.setAdapter(miscellaneousAdapter);

        return view;
    }
}
