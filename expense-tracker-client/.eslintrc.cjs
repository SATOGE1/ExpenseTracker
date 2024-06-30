/* eslint-env node */
module.exports = {
  root: true,
  'extends': [
    'plugin:vue/vue3-essential',
    'eslint:recommended',
  ],
  parserOptions: {
    ecmaVersion: 'latest',
  },
  rules: {
    'linebreak-style' : 0,
    'comma-dangle': ['error', 'always-multiline'],
    'semi': ['error', 'always'],
  },
};
