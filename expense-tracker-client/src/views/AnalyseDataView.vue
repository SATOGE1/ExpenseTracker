<template>
    <div class="container">
        <div class="form">
            <header>Analyse Expenses</header>
            <Chart type="doughnut" :data="chartData" :options="chartOptions" class="w-full md:w-[30rem]" />
        </div>
    </div>
</template>

<script setup>
import '@vuepic/vue-datepicker/dist/main.css';
import { useCategoriesStore } from '@/stores/categoriesStore';
import { computed } from "vue";
import Chart from 'primevue/chart';

const categoriesStore = useCategoriesStore();
categoriesStore.getCategories();

const categoryTitles = computed(() => {
    const categoryTitles = [];

    if (null != categoriesStore.categories) {
        for (let i = 0; i < categoriesStore.categories.length; i++) {
            if(categoriesStore.categories[i].total !=  0) {
                categoryTitles.push(categoriesStore.categories[i].title);
            }
        }
    }

    return categoryTitles;
});

const categoryTotals = computed(() => {
    const categoryTotals = [];

    if (null != categoriesStore.categories) {
        for (let i = 0; i < categoriesStore.categories.length; i++) {
            if(categoriesStore.categories[i].total !=  0) {
                categoryTotals.push(categoriesStore.categories[i].total);
            }
        }
    }

    return categoryTotals;
});

const chartData = computed(() => {
    categoriesStore.getExpenseTotalsByCategory;

    return {
        labels: categoryTitles.value,
        datasets: [
            {
                data: categoryTotals.value,
            },
        ],
    };
});

const chartOptions = computed(() => {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--p-text-color');

    return {
        plugins: {
            legend: {
                labels: {
                    cutout: '60%',
                    color: textColor,
                },
            },
        },
    };
});
</script>

<style scoped>
.container{
    max-width: 430px;
    background: rgba(50, 50, 50, 0.5);
    border-radius: 7px;
}
.container .registration{
    display: none;
}
.container .form{
    padding: 2rem;
}
.form header{
    font-size: 2rem;
    font-weight: 500;
    text-align: center;
    margin-bottom: 1.5rem;
}
.form input, .form select{
    height: 50px;
    width: 100%;
    padding: 0 13px;
    font-size: 15px;
    margin-bottom: 1.3rem;
    border: 1px solid #ddd;
    border-radius: 6px;
    outline: none;
}
.form input.button{
    color: #fff;
    background: rgba(255, 123, 172, 1);;
    font-size: 1.2rem;
    font-weight: 500;
    letter-spacing: 1px;
    margin-top: 1.7rem;
    cursor: pointer;
    transition: 0.4s;
}
.form input.button:hover{
    background: rgba(255, 123, 172, 0.8);
}
.axis{
    display: none !important;
}
</style>
