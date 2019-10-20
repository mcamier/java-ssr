import 'core-js/features/set';
import 'core-js/features/map';
import React from 'react';
import ReactDOM from 'react-dom';
import ReactDOMServer from 'react-dom/server';
import './index.css';
import App from './App';

console.log(ReactDOMServer.renderToString(React.createElement(App, {})));
