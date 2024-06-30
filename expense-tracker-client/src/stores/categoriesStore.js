import { defineStore } from "pinia";
import { ref } from "vue";
import { useAuthStore } from '@/stores/authStore';

const authStore = useAuthStore();

export const useCategoriesStore = defineStore('categories', () => {
    const categories = ref(null);

    const getCategories = async () => {
        const response = await fetch('/api/categories', {
            headers: {Authorization: 'Bearer ' + authStore.getToken},
        });

        categories.value = await response.json();
    };

    return { categories, getCategories };
});