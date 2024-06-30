import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createPinia } from 'pinia';
import axios from 'axios';
import VueAxios from 'vue-axios';
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
import PrimeVue from 'primevue/config';
import Aura from '@primevue/themes/aura';


const app = createApp(App);
const store = createPinia();

app.use(router);
app.use(store);
app.use(VueAxios, axios);

app.use(PrimeVue, {
    theme: {
        preset: Aura,
    },
});

app.component('VueDatePicker', VueDatePicker);

app.mount('#app');
