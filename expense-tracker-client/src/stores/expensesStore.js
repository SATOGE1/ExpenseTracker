import { defineStore } from "pinia";
import { ref, computed } from "vue";
import { useAuthStore } from '@/stores/authStore';
import { useCategoriesStore } from '@/stores/categoriesStore';

const authStore = useAuthStore();

const categoriesStore = useCategoriesStore();
categoriesStore.getCategories();

export const useExpensesStore = defineStore('expenses', () => {
    const expenses = ref(null);
    const description = ref("");
    const amount = ref(0);
    const categoryId = ref(1);
    const date = ref();

    if (localStorage.getItem("description")) {
        description.value = localStorage.getItem("description");
    }

    if (localStorage.getItem("amount")) {
        amount.value = localStorage.getItem("amount");
    }

    if (localStorage.getItem("categoryId")) {
        categoryId.value = localStorage.getItem("categoryId");
    }

    if (localStorage.getItem("date")) {
        date.value = localStorage.getItem("date");
    }

    const getDescription = computed(() => {
        return description.value;
    });

    const getAmount = computed(() => {
        return amount.value;
    });

    const getCategoryId = computed(() => {
        return categoryId.value;
    });

    const getDate = computed(() => {
        return date.value;
    });

    const getExpenses = async () => {
        const response = await fetch('/api/expenses', {
            headers: {Authorization: 'Bearer ' + authStore.getToken},
        });

        expenses.value = await response.json();
    };

    const setSelectedExpense = (newDescription, newAmount, newCategoryId, newDate) => {
        description.value = newDescription;
        amount.value = newAmount;
        categoryId.value = newCategoryId;
        date.value = newDate;
    };

    const clearSelected = () => {
        description.value = "";
        amount.value = 0;
        categoryId.value = 1;
        date.value = null;
        localStorage.removeItem('description');
        localStorage.removeItem('amount');
        localStorage.removeItem('categoryId');
        localStorage.removeItem('date');
    };

    return { expenses,
        getDescription,
        getAmount,
        getCategoryId,
        getDate,
        getExpenses, 
        setSelectedExpense,
        clearSelected };
});