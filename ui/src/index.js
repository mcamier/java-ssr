import React from 'react';
import ReactDOM from 'react-dom';
import ReactDOMServer from 'react-dom/server';
import './index.css';
import App from './App';

console.log("test");
console.log(ReactDOMServer.renderToString(React.createElement('p', null, "PUTAIN DE DIEU")));