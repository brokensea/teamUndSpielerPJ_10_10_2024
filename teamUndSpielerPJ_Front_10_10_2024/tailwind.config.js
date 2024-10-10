/** @type {import('tailwindcss').Config} */
import daisyui from 'daisyui';
export default {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
    "./node_modules/daisyui/**/*.js",
  ],
  theme: {
    extend: {},
  },
  plugins: [daisyui],
}


