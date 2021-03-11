import sirv from 'sirv';
import polka from 'polka';
import compression from 'compression';
import * as sapper from '@sapper/server';
import { json } from 'body-parser';
import session from 'express-session';
import sessionFileStore from 'session-file-store';

const { PORT, NODE_ENV } = process.env;
const dev = NODE_ENV === 'development';

const FileStore = new sessionFileStore(session);

polka() // You can also use Express
	.use(
		compression({ threshold: 0 }),
		sirv('static', { dev }),
		json(),
		session({
			secret: 'ASecretForNow',
			resave: true,
			saveUninitialized: true,
			cookie: {
				maxAge: 31536000
			},
			store: new FileStore({
				path: `.sessions`
			})
		}),
		sapper.middleware()
	)
	.listen(PORT, err => {
		if (err) console.log('error', err);
	});
